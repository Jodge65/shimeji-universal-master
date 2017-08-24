package com.jodge.shimeji;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Translator
{
	public enum Langue
	{
		EN("en"),
		JAP("jap"),
		FR("fr")
		;
		
		protected String name;
		Langue(String name)
		{
			this.name = name;
		}
		
		public String toString()
		{
			return this.name;
		}
	}
	
	protected Map<String, List<String>> translate;
	protected Map<String, Integer> genericId;
	
	protected static Translator instance;
	protected static int currentId = 0;
	
	public static Translator getInstance()
	{
		if(instance == null)
			instance = new Translator();
		return instance;
	}
	
	protected Translator()
	{
		translate = new HashMap<String, List<String>>();
		genericId = new HashMap<String, Integer>();
		init();
	}
	
	
	
	
	public Translator addLanguage(String name)
	{
		return addLanguage(name, new ArrayList<String>());
	}
	
	public Translator addLanguage(String name, List<String> value)
	{
		translate.put(name, value);
		return this;
	}
	
	public List<String> getLanguage(String name)
	{
		return translate.get(name);
	}
	
	public String translateWord(String word, String fromLanguage, String toLanguage)
	{
		return translate.get(toLanguage).get(translate.get(fromLanguage).indexOf(word));
	}
	public String translateLine(String line, String fromLanguage, String toLanguage)
	{
		List<String> from = getLanguage(fromLanguage);
		List<String> to = getLanguage(toLanguage);
		int nbWords = to.size();
		
		for(int i = 0; i < nbWords; i++)
		{
			line = Normalizer.normalize(line, Normalizer.Form.NFKC);
			line = line.replace(from.get(i), to.get(i));
		}
		return line;
	}
	public Translator addWord(String word, Langue language, String englishIndex)
	{
		int id;
		if(genericId.containsKey(englishIndex))
		{
			id = genericId.get(englishIndex);
		}
		else
		{
			id = currentId;
			genericId.put(englishIndex, currentId);
			for(Entry<String, List<String>> pair : translate.entrySet())
			{
				pair.getValue().add("<undefined>");
			}
			currentId++;
		}
		
		translate.get(language.toString()).set(id, word);
		
		return this;
	}
	
	protected void init()
	{
		List<String> en = new ArrayList<String>();
		List<String> jap = new ArrayList<String>();
		List<String> fr = new ArrayList<String>();
		
		
		
		jap.add("<マスコット ");
		fr.add("");
		en.add("<Mascot ");
		jap.add("<動作リスト>");
		fr.add("");
		en.add("<ActionList>");
		jap.add("<動作 ");
		fr.add("");
		en.add("<Action ");
		jap.add("名前=");
		fr.add("");
		en.add("Name=");
		jap.add("\"振り向く\"");
		fr.add("");
		en.add("\"Look\"");
		jap.add("種類=");
		fr.add("");
		en.add("Type=");
		jap.add("\"組み込み\"");
		fr.add("");
		en.add("\"Embedded\"");
		jap.add("クラス=");
		fr.add("");
		en.add("Class=");
		jap.add("\"変位\"");
		fr.add("");
		en.add("\"Offset\"");
		jap.add("<!-- 立つ系 -->");
		fr.add("");
		en.add("<!-- Standing -->");
		jap.add("\"立つ\"");
		fr.add("");
		en.add("\"Stand\"");
		jap.add("\"静止\"");
		fr.add("");
		en.add("\"Stay\"");
		jap.add("枠=");
		fr.add("");
		en.add("BorderType=");
		jap.add("\"地面\"");
		fr.add("");
		en.add("\"Floor\"");
		jap.add("<アニメーション>");
		fr.add("");
		en.add("<Animation>");
		jap.add("<ポーズ ");
		fr.add("");
		en.add("<Pose ");
		jap.add("画像=");
		fr.add("");
		en.add("Image=");
		jap.add("基準座標=");
		fr.add("");
		en.add("ImageAnchor=");
		jap.add("移動速度=");
		fr.add("");
		en.add("Velocity=");
		jap.add("長さ=");
		fr.add("");
		en.add("Duration=");
		jap.add("</アニメーション>");
		fr.add("");
		en.add("</Animation>");
		jap.add("</動作>");
		fr.add("");
		en.add("</Action>");
		jap.add("\"歩く\"");
		fr.add("");
		en.add("\"Walk\"");
		jap.add("\"移動\"");
		fr.add("");
		en.add("\"Move\"");
		jap.add("\"走る\"");
		fr.add("");
		en.add("\"Run\"");
		jap.add("\"猛ダッシュ\"");
		fr.add("");
		en.add("\"Dash\"");
		jap.add("<!-- 座る系 -->");
		fr.add("");
		en.add("<!-- Sitting -->");
		jap.add("\"座る\"");
		fr.add("");
		en.add("\"Sit\"");
		jap.add("\"座って見上げる\"");
		fr.add("");
		en.add("\"SitAndLookUp\"");
		jap.add("\"座ってマウスを見上げる\"");
		fr.add("");
		en.add("\"SitAndLookAtMouse\"");
		jap.add("<アニメーション ");
		fr.add("");
		en.add("<Animation ");
		jap.add("条件=");
		fr.add("");
		en.add("Condition=");
		jap.add("</アニメーション>");
		fr.add("");
		en.add("</Animation>");
		jap.add("\"座って首が回る\"");
		fr.add("");
		en.add("\"SitAndSpinHeadAction\"");
		jap.add("\"固定\"");
		fr.add("");
		en.add("\"Animate\"");
		jap.add("\"楽に座る\"");
		fr.add("");
		en.add("\"SitWithLegsUp\"");
		jap.add("\"足を下ろして座る\"");
		fr.add("");
		en.add("\"SitWithLegsDown\"");
		jap.add("\"足をぶらぶらさせる\"");
		fr.add("");
		en.add("\"SitAndDangleLegs\"");
		jap.add("<!-- 寝そべる系 -->");
		fr.add("");
		en.add("<!-- Laying -->");
		jap.add("\"寝そべる\"");
		fr.add("");
		en.add("\"Sprawl\"");
		jap.add("\"ずりずり\"");
		fr.add("");
		en.add("\"Creep\"");
		jap.add("<!-- 天井系 -->");
		fr.add("");
		en.add("<!-- Ceiling -->");
		jap.add("\"天井に掴まる\"");
		fr.add("");
		en.add("\"GrabCeiling\"");
		jap.add("\"天井\"");
		fr.add("");
		en.add("\"Ceiling\"");
		jap.add("\"天井を伝う\"");
		fr.add("");
		en.add("\"ClimbCeiling\"");
		jap.add("<!-- 壁系 -->");
		fr.add("");
		en.add("<!-- Wall -->");
		jap.add("\"壁に掴まる\"");
		fr.add("");
		en.add("\"GrabWall\"");
		jap.add("\"壁\"");
		fr.add("");
		en.add("\"Wall\"");
		jap.add("\"壁を登る\"");
		fr.add("");
		en.add("\"ClimbWall\"");
		jap.add("目的地Y");
		fr.add("");
		en.add("TargetY");
		jap.add("目的地X");
		fr.add("");
		en.add("TargetX");
		jap.add("<!-- IE系 -->");
		fr.add("");
		en.add("<!-- IE -->");
		jap.add("\"IEを持って落ちる\"");
		fr.add("");
		en.add("\"FallWithIe\"");
		jap.add("IEの端X=");
		fr.add("");
		en.add("IeOffsetX=");
		jap.add("IEの端Y=");
		fr.add("");
		en.add("IeOffsetY=");
		jap.add("\"IEを持って歩く\"");
		fr.add("");
		en.add("\"WalkWithIe\"");
		jap.add("\"IEを持って走る\"");
		fr.add("");
		en.add("\"RunWithIe\"");
		jap.add("\"IEを投げる\"");
		fr.add("");
		en.add("\"ThrowIe\"");
		jap.add("初速X=");
		fr.add("");
		en.add("InitialVX=");
		jap.add("初速Y=");
		fr.add("");
		en.add("InitialVY=");
		jap.add("重力=");
		fr.add("");
		en.add("Gravity=");
		jap.add("<!-- 落下系 -->");
		fr.add("");
		en.add("<!-- Falling -->");
		jap.add("\"ジャンプ\"");
		fr.add("");
		en.add("\"Jumping\"");
		jap.add("速度=");
		fr.add("");
		en.add("VelocityParam=");
		jap.add("\"落ちる\"");
		fr.add("");
		en.add("\"Falling\"");
		jap.add("空気抵抗X=");
		fr.add("");
		en.add("RegistanceX=");
		jap.add("空気抵抗Y=");
		fr.add("");
		en.add("RegistanceY=");
		jap.add("\"跳ねる\"");
		fr.add("");
		en.add("\"Bouncing\"");
		jap.add("\"転ぶ\"");
		fr.add("");
		en.add("\"Tripping\"");
		jap.add("<!-- ドラッグ系 -->");
		fr.add("");
		en.add("<!-- Dragging -->");
		jap.add("\"つままれる\"");
		fr.add("");
		en.add("\"Pinched\"");
		jap.add("\"抵抗する\"");
		fr.add("");
		en.add("\"Resisting\"");
		jap.add("</動作リスト>");
		fr.add("");
		en.add("</ActionList>");
		jap.add("<!-- 実際の行動 -->");
		fr.add("");
		en.add("<!-- Actual Behavior -->");
		jap.add("<!-- システムが使用する -->");
		fr.add("");
		en.add("<!-- ALWAYS REQUIRED -->");
		jap.add("\"落下する\"");
		fr.add("");
		en.add("\"Fall\"");
		jap.add("\"複合\"");
		fr.add("");
		en.add("\"Sequence\"");
		jap.add("繰り返し=");
		fr.add("");
		en.add("Loop=");
		jap.add("<動作参照 ");
		fr.add("");
		en.add("<ActionReference ");
		jap.add("\"選択\"");
		fr.add("");
		en.add("\"Select\"");
		jap.add("\"ドラッグされる\"");
		fr.add("");
		en.add("\"Dragged\"");
		jap.add("\"投げられる\"");
		fr.add("");
		en.add("\"Thrown\"");
		jap.add("\"立ってボーっとする\"");
		fr.add("");
		en.add("\"StandUp\"");
		jap.add("\"座ってボーっとする\"");
		fr.add("");
		en.add("\"SitDown\"");
		jap.add("\"寝そべってボーっとする\"");
		fr.add("");
		en.add("\"LieDown\"");
		jap.add("\"座って足をぶらぶらさせる\"");
		fr.add("");
		en.add("\"SitWhileDanglingLegs\"");
		jap.add("\"壁に掴まってボーっとする\"");
		fr.add("");
		en.add("\"HoldOntoWall\"");
		jap.add("\"壁から落ちる\"");
		fr.add("");
		en.add("\"FallFromWall\"");
		jap.add("\"天井に掴まってボーっとする\"");
		fr.add("");
		en.add("\"HoldOntoCeiling\"");
		jap.add("\"天井から落ちる\"");
		fr.add("");
		en.add("\"FallFromCeiling\"");
		jap.add("\"ワークエリアの下辺を歩く\"");
		fr.add("");
		en.add("\"WalkAlongWorkAreaFloor\"");
		jap.add("\"ワークエリアの下辺を走る\"");
		fr.add("");
		en.add("\"RunAlongWorkAreaFloor\"");
		jap.add("\"ワークエリアの下辺でずりずり\"");
		fr.add("");
		en.add("\"CrawlAlongWorkAreaFloor\"");
		jap.add("\"ワークエリアの下辺の左の端っこで座る\"");
		fr.add("");
		en.add("\"WalkLeftAlongFloorAndSit\"");
		jap.add("\"ワークエリアの下辺の右の端っこで座る\"");
		fr.add("");
		en.add("\"WalkRightAlongFloorAndSit\"");
		jap.add("右向き=");
		fr.add("");
		en.add("LookRight=");
		jap.add("\"ワークエリアの下辺から左の壁によじのぼる\"");
		fr.add("");
		en.add("\"GrabWorkAreaBottomLeftWall\"");
		jap.add("\"ワークエリアの下辺から右の壁によじのぼる\"");
		fr.add("");
		en.add("\"GrabWorkAreaBottomRightWall\"");
		jap.add("\"走ってワークエリアの下辺の左の端っこで座る\"");
		fr.add("");
		en.add("\"WalkLeftAndSit\"");
		jap.add("\"走ってワークエリアの下辺の右の端っこで座る\"");
		fr.add("");
		en.add("\"WalkRightAndSit\"");
		jap.add("\"走ってワークエリアの下辺から左の壁によじのぼる\"");
		fr.add("");
		en.add("\"WalkAndGrabBottomLeftWall\"");
		jap.add("\"走ってワークエリアの下辺から右の壁によじのぼる\"");
		fr.add("");
		en.add("\"WalkAndGrabBottomRightWall\"");
		jap.add("\"IEの下に飛びつく\"");
		fr.add("");
		en.add("\"JumpFromBottomOfIE\"");
		jap.add("\"ワークエリアの壁を途中まで登る\"");
		fr.add("");
		en.add("\"ClimbHalfwayAlongWall\"");
		jap.add("<行動リスト>");
		fr.add("");
		en.add("<BehaviorList>");
		jap.add("<行動 ");
		fr.add("");
		en.add("<Behavior ");
		jap.add("頻度=");
		fr.add("");
		en.add("Frequency=");
		jap.add("<次の行動リスト ");
		fr.add("");
		en.add("<NextBehavior ");
		jap.add("追加=");
		fr.add("");
		en.add("Add=");
		jap.add("</次の行動リスト>");
		fr.add("");
		en.add("</NextBehavior>");
		jap.add("</行動>");
		fr.add("");
		en.add("</Behavior>");
		jap.add("\"マウスの周りに集まる\"");
		fr.add("");
		en.add("\"ChaseMouse\"");
		jap.add("<行動参照 ");
		fr.add("");
		en.add("<BehaviorReference ");
		jap.add("\"座ってマウスのほうを見る\"");
		fr.add("");
		en.add("\"SitAndFaceMouse\"");
		jap.add("\"座ってマウスのほうを見てたら首が回った\"");
		fr.add("");
		en.add("\"SitAndSpinHead\"");
		jap.add("\"引っこ抜かれる\"");
		fr.add("");
		en.add("\"PullUp\"");
		jap.add("\"分裂した\"");
		fr.add("");
		en.add("\"Divided\"");
		jap.add("<!-- 地面に接しているとき -->");
		fr.add("");
		en.add("<!-- On the Floor -->");
		jap.add("<条件 ");
		fr.add("");
		en.add("<Condition ");
		jap.add("\"分裂する\"");
		fr.add("");
		en.add("\"SplitIntoTwo\"");
		jap.add("</条件>");
		fr.add("");
		en.add("</Condition>");
		jap.add("<!-- 壁に接しているとき -->");
		fr.add("");
		en.add("<!-- On the Wall -->");
		jap.add("<!-- 天井に接しているとき -->");
		fr.add("");
		en.add("<!-- On the Ceiling -->");
		jap.add("<!-- ワークエリアの下辺に接しているとき -->");
		fr.add("");
		en.add("<!-- On Work Area Floor -->");
		jap.add("<!-- ずりずりした後はそのままボーっとする -->");
		fr.add("");
		en.add("<!-- Finished Crawling -->");
		jap.add("\"引っこ抜く\"");
		fr.add("");
		en.add("\"PullUpShimeji\"");
		jap.add("<!-- ワークエリアの壁に接しているとき -->");
		fr.add("");
		en.add("<!-- On Work Area Facing the Wall -->");
		jap.add("\"ワークエリアの壁を登る\"");
		fr.add("");
		en.add("\"ClimbAlongWall\"");
		jap.add("<!-- ワークエリアの上辺に接しているとき -->");
		fr.add("");
		en.add("<!-- On Work Area Top Facing -->");
		jap.add("\"ワークエリアの上辺を伝う\"");
		fr.add("");
		en.add("\"ClimbAlongCeiling\"");
		jap.add("<!-- IEの上辺に接しているとき -->");
		fr.add("");
		en.add("<!-- On Top of IE -->");
		jap.add("\"IEの天井を歩く\"");
		fr.add("");
		en.add("\"WalkAlongIECeiling\"");
		jap.add("\"IEの天井を走る\"");
		fr.add("");
		en.add("\"RunAlongIECeiling\"");
		jap.add("\"IEの天井でずりずり\"");
		fr.add("");
		en.add("\"CrawlAlongIECeiling\"");
		jap.add("<!-- ずりずりした後はそのままボーっとする -->");
		fr.add("");
		en.add("<!-- Finished Crawling -->");
		jap.add("\"IEの天井の左の端っこで座る\"");
		fr.add("");
		en.add("\"SitOnTheLeftEdgeOfIE\"");
		jap.add("\"IEの天井の右の端っこで座る\"");
		fr.add("");
		en.add("\"SitOnTheRightEdgeOfIE\"");
		jap.add("\"IEの天井の左の端っこから飛び降りる\"");
		fr.add("");
		en.add("\"JumpFromLeftEdgeOfIE\"");
		jap.add("\"IEの天井の右の端っこから飛び降りる\"");
		fr.add("");
		en.add("\"JumpFromRightEdgeOfIE\"");
		jap.add("\"走ってIEの天井の左の端っこで座る\"");
		fr.add("");
		en.add("\"WalkLeftAlongIEAndSit\"");
		jap.add("\"走ってIEの天井の右の端っこで座る\"");
		fr.add("");
		en.add("\"WalkRightAlongIEAndSit\"");
		jap.add("\"走ってIEの天井の左の端っこから飛び降りる\"");
		fr.add("");
		en.add("\"WalkLeftAlongIEAndJump\"");
		jap.add("\"走ってIEの天井の右の端っこから飛び降りる\"");
		fr.add("");
		en.add("\"WalkRightAlongIEAndJump\"");
		jap.add("<!-- IEの壁に接しているとき -->");
		fr.add("");
		en.add("<!-- On IE's Side -->");
		jap.add("\"IEの壁を途中まで登る\"");
		fr.add("");
		en.add("\"HoldOntoIEWall\"");
		jap.add("\"IEの壁を登る\"");
		fr.add("");
		en.add("\"ClimbIEWall\"");
		jap.add("<!-- IEの下辺に接しているとき -->");
		fr.add("");
		en.add("<!-- On the Bottom of IE -->");
		jap.add("\"IEの下辺を伝う\"");
		fr.add("");
		en.add("\"ClimbIEBottom\"");
		jap.add("\"IEの下辺から左の壁によじのぼる\"");
		fr.add("");
		en.add("\"GrabIEBottomLeftWall\"");
		jap.add("\"IEの下辺から右の壁によじのぼる\"");
		fr.add("");
		en.add("\"GrabIEBottomRightWall\"");
		jap.add("\"左の壁に飛びつく\"");
		fr.add("");
		en.add("\"JumpFromLeftWall\"");
		jap.add("\"右の壁に飛びつく\"");
		fr.add("");
		en.add("\"JumpFromRightWall\"");
		jap.add("<!-- IEが見えるとき -->");
		fr.add("");
		en.add("<!-- IE Is Visible -->");
		jap.add("\"IEの左に飛びつく\"");
		fr.add("");
		en.add("\"JumpOnIELeftWall\"");
		jap.add("\"IEの右に飛びつく\"");
		fr.add("");
		en.add("\"JumpOnIERightWall\"");
		jap.add("\"IEを右に投げる\"");
		fr.add("");
		en.add("\"ThrowIEFromLeft\"");
		jap.add("\"IEを左に投げる\"");
		fr.add("");
		en.add("\"ThrowIEFromRight\"");
		jap.add("\"走ってIEを右に投げる\"");
		fr.add("");
		en.add("\"WalkAndThrowIEFromRight\"");
		jap.add("\"走ってIEを左に投げる\"");
		fr.add("");
		en.add("\"WalkAndThrowIEFromLeft\"");
		jap.add("</行動リスト>");
		fr.add("");
		en.add("</BehaviorList>");
		jap.add("</マスコット>");
		fr.add("");
		en.add("</Mascot>");
		jap.add("\"猛ダッシュでIEの天井の左の端っこから飛び降りる\"");
		fr.add("");
		en.add("\"DashIeCeilingLeftEdgeFromJump\"");
		jap.add("\"猛ダッシュでIEの天井の右の端っこから飛び降りる\"");
		fr.add("");
		en.add("\"DashIeCeilingRightEdgeFromJump\"");
		jap.add("\"猛ダッシュでIEの天井の左の端っこから飛び降りる\"");
		fr.add("");
		en.add("\"DashIeCeilingLeftEdgeFromJump\"");
		jap.add("\"猛ダッシュでIEの天井の右の端っこから飛び降りる\"");
		fr.add("");
		en.add("\"DashIeCeilingRightEdgeFromJump\"");
		jap.add("ずれ=");
		fr.add("");
		en.add("Gap=");
		jap.add("\"引っこ抜く1\"");
		fr.add("");
		en.add("\"PullUpShimeji1\"");
		jap.add("生まれる場所X=");
		fr.add("");
		en.add("BornX=");
		jap.add("生まれる場所Y=");
		fr.add("");
		en.add("BornY=");
		jap.add("生まれた時の行動=");
		fr.add("");
		en.add("BornBehavior=");
		jap.add("\"引っこ抜く2\"");
		fr.add("");
		en.add("\"PullUpShimeji2\"");
		jap.add("\"分裂1\"");
		fr.add("");
		en.add("\"Divide1\"");
		jap.add("{footX");
		fr.add("");
		en.add("{FootX");		
		jap.add("ずれ");
		fr.add("");
		en.add("Gap");	
		
		int nbWord = en.size();
		for(currentId = 0; currentId < nbWord; currentId++)
		{
			String neededWord = en.get(currentId);
			genericId.put(neededWord, currentId);
		}
		
		addLanguage(Langue.EN.toString(), en);
		addLanguage(Langue.JAP.toString(), jap);
		addLanguage(Langue.FR.toString(), fr);
	}

	
}
