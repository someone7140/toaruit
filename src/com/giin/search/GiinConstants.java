

package com.giin.search;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiinConstants {

    // 都道府県マップ
	public static final Map<Integer ,String> TODOUHUKEN_MAP;
    static {
        HashMap<Integer ,String> map = new HashMap<Integer ,String>();
        map.put(1, "北海道");
        map.put(2, "青森県");
        map.put(3, "岩手県");
        map.put(4, "宮城県");
        map.put(5, "秋田県");
        map.put(6, "山形県");
        map.put(7, "福島県");
        map.put(8, "茨城県");
        map.put(9, "栃木県");
        map.put(10,"群馬県");
        map.put(11,"埼玉県");
        map.put(12,"千葉県");
        map.put(13,"東京都");
        map.put(14,"神奈川県");
        map.put(15,"新潟県");
        map.put(16,"富山県");
        map.put(17,"石川県");
        map.put(18,"福井県");
        map.put(19,"山梨県");
        map.put(20,"長野県");
        map.put(21,"岐阜県");
        map.put(22,"静岡県");
        map.put(23,"愛知県");
        map.put(24,"三重県");
        map.put(25,"滋賀県");
        map.put(26,"京都府");
        map.put(27,"大阪府");
        map.put(28,"兵庫県");
        map.put(29,"奈良県");
        map.put(30,"和歌山県");
        map.put(31,"鳥取県");
        map.put(32,"島根県");
        map.put(33,"岡山県");
        map.put(34,"広島県");
        map.put(35,"山口県");
        map.put(36,"徳島県");
        map.put(37,"香川県");
        map.put(38,"愛媛県");
        map.put(39,"高知県");
        map.put(40,"福岡県");
        map.put(41,"佐賀県");
        map.put(42,"長崎県");
        map.put(43,"熊本県");
        map.put(44,"大分県");
        map.put(45,"宮崎県");
        map.put(46,"鹿児島県");
        map.put(47,"沖縄県");
        map.put(48,"全国（比例）");
        TODOUHUKEN_MAP = map;
    }

    // 都道府県ブロックマップ
	public static final Map<Integer ,String> TODOUHUKEN_BLOCK_MAP;
    static {
        HashMap<Integer ,String> map = new HashMap<Integer ,String>();
        map.put(1, "北海道ブロック");
        map.put(2, "東北ブロック");
        map.put(3, "東北ブロック");
        map.put(4, "東北ブロック");
        map.put(5, "東北ブロック");
        map.put(6, "東北ブロック");
        map.put(7, "東北ブロック");
        map.put(8, "北関東ブロック");
        map.put(9, "北関東ブロック");
        map.put(10,"北関東ブロック");
        map.put(11,"北関東ブロック");
        map.put(12,"南関東ブロック");
        map.put(13,"東京ブロック");
        map.put(14,"南関東ブロック");
        map.put(15,"北陸信越ブロック");
        map.put(16,"北陸信越ブロック");
        map.put(17,"北陸信越ブロック");
        map.put(18,"北陸信越ブロック");
        map.put(19,"南関東ブロック");
        map.put(20,"北陸信越ブロック");
        map.put(21,"東海ブロック");
        map.put(22,"東海ブロック");
        map.put(23,"東海ブロック");
        map.put(24,"東海ブロック");
        map.put(25,"近畿ブロック");
        map.put(26,"近畿ブロック");
        map.put(27,"近畿ブロック");
        map.put(28,"近畿ブロック");
        map.put(29,"近畿ブロック");
        map.put(30,"近畿ブロック");
        map.put(31,"中国ブロック");
        map.put(32,"中国ブロック");
        map.put(33,"中国ブロック");
        map.put(34,"中国ブロック");
        map.put(35,"中国ブロック");
        map.put(36,"四国ブロック");
        map.put(37,"四国ブロック");
        map.put(38,"四国ブロック");
        map.put(39,"四国ブロック");
        map.put(40,"九州ブロック");
        map.put(41,"九州ブロック");
        map.put(42,"九州ブロック");
        map.put(43,"九州ブロック");
        map.put(44,"九州ブロック");
        map.put(45,"九州ブロック");
        map.put(46,"九州ブロック");
        map.put(47,"九州ブロック");
        map.put(48,"全国（比例）");
        TODOUHUKEN_BLOCK_MAP = map;
    }
    
    // 衆議院議員の一覧ワード
    public static final String SYUUGIIN_ICHIRAN = "衆議院議員一覧";

    // 参議院議員の一覧ワード
    public static final String SANGIIN_ICHIRAN = "参議院議員一覧";
    
    // 都道府県知事の一覧ワード
    public static final String CHIJI_ICHIRAN = "都道府県知事の一覧";
    
    // 衆議院ブロック
	public static final List<String> SYUUGIIN_BLOCK = Collections.unmodifiableList(Arrays.asList("北海道ブロック", "東北ブロック","北関東ブロック","南関東ブロック",
			                                                                                    "東京ブロック","北陸信越ブロック","東海ブロック","近畿ブロック","中国ブロック",
			                                                                                    "四国ブロック","九州ブロック"));
	
    // 任期
	public static final String NINKI = "任期";
    // 任期満了
	public static final String NINKI_MANRYOU = "任期満了";
	
    // 年パターン
	public static final String YEAR_PATTERN = "年";
	
    // 月日パターン
	public static final String MONTHDAY_PATTERN = "月.*?日";
	
    // 小選挙区
	public static final String SYOUSENKYOKU_ID = "1";
	
    // 比例
	public static final String HIREI_ID = "2";
	
    // 区のワード
	public static final String WORD_KU = "区";
	
    // Largerのワード
	public static final String WORD_LARGER = "Larger";
	
    // 選挙区のワード
	public static final String WORD_SENKYOKU = "選挙区";
	
    // 一覧のワード
	public static final String WORD_ICHIRAN = "一覧";
	
	// 就任者の傾向のワード
	public static final String WORD_KEIKOU = "就任者の傾向";
	
    // 全角括弧の始まり
	public static final String ZENKAKU_KAKKO_START = "（";
	
    // 全角括弧の終わり
	public static final String ZENKAKU_KAKKO_END = "）";
	
    // 全角句点
	public static final String ZENKAKU_KUTEN = "、";

    // 半角句点
	public static final String HANKAKU_KUTEN = ",";
	
    // 全角ハイフン
	public static final String ZENKAKU_HAIFUN = "‐";
	
    // 全角スペース
	public static final String ZENKAKU_SPACE = "　";
	
    // 比例議員
	public static final String HIREI_SENSYUTSU_GIIN = "比例代表選出議員";
	
    // 選挙区選出議員
	public static final String SENKYOKU_SENSYUTSU_GIIN = "選挙区選出議員";
	
    // 比例・全国のマップID
	public static final int HIREI_ZENKOKU_MAPID = 48;
	
    // 会派別所属議員数
	public static final String KAIHABETU_SYOZOKU = "会派別所属議員数";
	
    // 人数_名
	public static final String NINZUU_MEI = "名";
	
    // 人数_人
	public static final String NINZUU_NIN = "人";
	
    // 政党区切り
	public static final String SEITOU_KUGIRI = "・";
	
    // 改選
	public static final String WORD_KAISEN = "改選時";
	
    // 中退
	public static final String WORD_CHUUTAI = "(中退)";
	
    // Wikipediaの取得エラーフラグ
	public static final String WIKI_ERROR_FLG = "1";
}


