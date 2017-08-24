package com.group_finity.mascot.win.jna;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * Original Author: Yuki Yamada of Group Finity (http://www.group-finity.com/Shimeji/)
 * Currently developed by Shimeji-ee Group.
 */

public class SIZE extends Structure {

    public int cx;
    public int cy;

    @Override
    protected List getFieldOrder() {
        return Arrays.asList(new String[] {"cx", "cy"});
    }
}
