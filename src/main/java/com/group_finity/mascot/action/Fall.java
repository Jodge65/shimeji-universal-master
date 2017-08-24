package com.group_finity.mascot.action;

import com.group_finity.mascot.Mascot;
import com.group_finity.mascot.animation.Animation;
import com.group_finity.mascot.exception.LostGroundException;
import com.group_finity.mascot.exception.VariableException;
import com.group_finity.mascot.script.VariableMap;

import java.awt.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Original Author: Yuki Yamada of Group Finity (http://www.group-finity.com/Shimeji/)
 * Currently developed by Shimeji-ee Group.
 */
public class Fall extends ActionBase {

    public static final String PARAMETER_INITIALVX = "InitialVX";
    public static final String PARAMETER_REGISTANCEX = "RegistanceX";
    public static final String PARAMETER_REGISTANCEY = "RegistanceY";
    public static final String PARAMETER_GRAVITY = "Gravity";
    private static final Logger log = Logger.getLogger(Fall.class.getName());
    private static final int DEFAULT_INITIALVX = 0;
    private static final String PARAMETER_INITIALVY = "InitialVY";
    private static final int DEFAULT_INITIALVY = 0;
    private static final double DEFAULT_REGISTANCEX = 0.05;
    private static final double DEFAULT_REGISTANCEY = 0.1;
    private static final double DEFAULT_GRAVITY = 2;

    private double velocityX;

    private double velocityY;

    private double modX;

    private double modY;

    public Fall(final List<Animation> animations, final VariableMap params) {
        super(animations, params);
    }

    @Override
    public void init(final Mascot mascot) throws VariableException {
        super.init(mascot);

        this.setVelocityX(getInitialVx());
        this.setVelocityY(getInitialVy());
    }

    @Override
    public boolean hasNext() throws VariableException {

        Point pos = getMascot().getAnchor();
        boolean onBorder = false;
        if (getEnvironment().getFloor().isOn(pos)) {
            onBorder = true;
        }
        if (getEnvironment().getWall().isOn(pos)) {
            onBorder = true;
        }
        return super.hasNext() && !onBorder;
    }

    @Override
    protected void tick() throws LostGroundException, VariableException {

        if (this.getVelocityX() != 0) {
            getMascot().setLookRight(this.getVelocityX() > 0);
        }

        this.setVelocityX(this.getVelocityX() - (this.getVelocityX() * getRegistanceX()));
        this.setVelocityY(this.getVelocityY() - (this.getVelocityY() * getRegistanceY()) + getGravity());

        this.setModX(this.getModX() + (this.getVelocityX() % 1));
        this.setModY(this.getModY() + (this.getVelocityY() % 1));

        int dx = (int) this.getVelocityX() + (int) this.getModX();
        int dy = (int) this.getVelocityY() + (int) this.getModY();

        this.setModX(this.getModX() % 1);
        this.setModY(this.getModY() % 1);

        int dev = Math.max(Math.abs(dx), Math.abs(dy));
        if (dev < 1) {
            dev = 1;
        }

        Point start = getMascot().getAnchor();

        OUTER:
        for (int i = 0; i <= dev; ++i) {
            int x = start.x + dx * i / dev;
            int y = start.y + dy * i / dev;

            getMascot().setAnchor(new Point(x, y));
            if (dy > 0) {
                // HACK IE
                for (int j = -80; j <= 0; ++j) {
                    getMascot().setAnchor(new Point(x, y + j));
                    if (getEnvironment().getFloor(true).isOn(getMascot().getAnchor())) {
                        break OUTER;
                    }
                }
            }
            if (getEnvironment().getWall(true).isOn(getMascot().getAnchor())) {
                break;
            }
        }

        getAnimation().next(getMascot(), getTime());

    }

    private int getInitialVy() throws VariableException {
        return eval(PARAMETER_INITIALVY, Number.class, DEFAULT_INITIALVY).intValue();
    }

    private int getInitialVx() throws VariableException {
        return eval(PARAMETER_INITIALVX, Number.class, DEFAULT_INITIALVX).intValue();
    }

    private double getGravity() throws VariableException {
        return eval(PARAMETER_GRAVITY, Number.class, DEFAULT_GRAVITY).doubleValue();
    }

    private double getRegistanceY() throws VariableException {
        return eval(PARAMETER_REGISTANCEY, Number.class, DEFAULT_REGISTANCEY).doubleValue();
    }

    private double getRegistanceX() throws VariableException {
        return eval(PARAMETER_REGISTANCEX, Number.class, DEFAULT_REGISTANCEX).doubleValue();
    }

    private double getVelocityY() {
        return this.velocityY;
    }

    private void setVelocityY(final double velocityY) {
        this.velocityY = velocityY;
    }

    private double getVelocityX() {
        return this.velocityX;
    }

    private void setVelocityX(final double velocityX) {
        this.velocityX = velocityX;
    }

    private double getModX() {
        return this.modX;
    }

    private void setModX(final double modX) {
        this.modX = modX;
    }

    private double getModY() {
        return this.modY;
    }

    private void setModY(final double modY) {
        this.modY = modY;
    }

}
