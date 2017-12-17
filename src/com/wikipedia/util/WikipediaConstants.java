

package com.wikipedia.util;

public class WikipediaConstants {

    // XML取得用URL前半
	public static final String WIKIPEDIA_URL_FHALF = "https://ja.wikipedia.org/w/api.php?format=xml&action=query&prop=revisions&titles=";

    // XML取得用URL後半
	public static final String WIKIPEDIA_URL_LHALF = "&rvprop=content";
	
    // wikipediaリンク用URL
	public static final String WIKIPEDIA_URL_LINK = "https://ja.wikipedia.org/wiki/";
		
    // 見出しの記号
	public static final String WIKIPEDIA_MIDASHI = "=";
	
    // wikitableの始まり文字
	public static final String WIKITABLE_START = "\"wikitable\"";
	
    // wikitableの終わり文字
	public static final String WIKITABLE_END = "\\}";
	
    // バーティカルバーの正規表現
	public static final String VERTICAL_PATTERN = "\\|";
	
    // バーティカルバーの文字列
	public static final String VERTICAL_CONSTANTS = "|";
	
    // wikitableセルの始まり文字（エスケープあり）
	public static final String WIKITABLE_CELL_START_ESCAPE = "\\[\\[";
	
    // wikitableセルの終わり文字（エスケープあり）
	public static final String WIKITABLE_CELL_END_ESCAPE = "\\]\\]";
	
    // wikitableセルの始まり文字
	public static final String WIKITABLE_CELL_START = "[[";
	
    // wikitableセルの終わり文字
	public static final String WIKITABLE_CELL_END = "]]";
	
    // 括弧の始まり文字（エスケープあり）
	public static final String WIKITABLE_KAKKO_START_ESCAPE = "\\(";
	
    // 括弧の終わり文字（エスケープあり）
	public static final String WIKITABLE_KAKKO_END_ESCAPE = "\\)";
	
    // 括弧の始まり文字
	public static final String WIKITABLE_KAKKO_START = "(";
	
    // 括弧の終わり文字
	public static final String WIKITABLE_KAKKO_END = ")";
	
    // アスタリスク（エスケープあり）
	public static final String WIKIPEDIA_ASTALISK_ESCAPE = "\\*";
	
    // セミコロン
	public static final String WIKIPEDIA_SEMI_COLON = ";";
	
    // シングルクォート
	public static final String WIKIPEDIA_SINGLE_QUOT = "'";
	
    // ハイフン
	public static final String WIKIPEDIA_HAIFUN = "-";
	
    // ハイフン（エスケープあり）
	public static final String WIKIPEDIA_HAIFUN_ESCAPE = "\\-";
	
    // スペース
	public static final String WIKIPEDIA_SPACE = " ";
	
    // バーティカルバー（エスケープなし）
	public static final String VERTICAL_BAR = "|";
	
    // wikitableの見出し文字（エスケープあり）
	public static final String WIKITABLE_MIDASHI_ESCAPE = "\\- !";
	
    // wikitableの見出し文字（エスケープなし）
	public static final String WIKITABLE_MIDASHI = "- !";
	
    // BRタグ
	public static final String WIKIPEDELIA_BR_TAG = "<br>";

    // BRタグ（スペースあり）	
	public static final String WIKIPEDELIA_BR_TAG_WITHSPACE = "<br />";
	
    // 中括弧の始まり
	public static final String WIKIPEDELIA_CHUKAKKO_START = "{";
	
    // 中括弧の終わり
	public static final String WIKIPEDELIA_CHUKAKKO_END = "}";
	
    // 英語⇒日本語変換XML取得用URL前半
	public static final String WIKIPEDIA_URL_ENTOJP_FHALF = "https://en.wikipedia.org/w/api.php?format=xml&action=query&prop=langlinks&titles=";
	
    // 英語⇒日本語変換XML取得用URL後半
	public static final String WIKIPEDIA_URL_ENTOJP_LHALF = "&lllimit=";
	
    // 日本語の言語記号
	public static final String WIKIPEDIA_JP_LANG = "ja";
}


