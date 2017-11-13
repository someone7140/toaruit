

package com.music.search;

public class MusicConstants {

    // gracenoteのURLターゲット
	public static final String GRACENOTE_URL = "https://c11518976.web.cddbp.net/webapi/xml/1.0/";
    // ユーザ登録時のコマンド
	public static final String USER_REISTER_CMD = "REGISTER";
    // SINGLE_BEST_COVERのリクエストのモード
	public static final String MODE_SINGLE_BEST_COVER = "SINGLE_BEST_COVER";
    // リクエスト時の言語
	public static final String LANG_JPN = "jpn";
    // ALBUM_SEARCHのコマンド
	public static final String ALBUM_SEARCH_CMD = "ALBUM_SEARCH";
	// アーテイストのタイプ
	public static final String ARTIST_TYPE = "ARTIST";
	// 曲名のタイプ
	public static final String TRACK_TITLE_TYPE = "TRACK_TITLE";
	// アルバム名のタイプ
	public static final String ALBUM_TYPE = "ALBUM_TITLE";
	// EXTENDEDのOptionパラメータ
	public static final String OPTION_EXTENDED = "SELECT_EXTENDED";
	// EXTENDEDのOptionVALUE
	public static final String OPTION_EXTENDED_VALUE = "COVER,REVIEW,ARTIST_BIOGRAPHY,ARTIST_IMAGE,ARTIST_OET,MOOD,TEMPO";
	// DETAILのOptionパラメータ
	public static final String OPTION_DETAIL = "SELECT_DETAIL";
	// DETAILのOptionVALUE
	public static final String OPTION_DETAIL_VALUE = "GENRE:3LEVEL,MOOD:2LEVEL,TEMPO:3LEVEL,ARTIST_ORIGIN:4LEVEL,ARTIST_ERA:2LEVEL,ARTIST_TYPE:2LEVEL";
	// レスポンスのNoUnmatch
	public static final String RESPONSE_NO_MATCH = "NO_MATCH";
	// レスポンスのARTIST_IMAGEのURL属性
	public static final String RESPONSE_URL_ARTIST_IMAGE_ATTR = "ARTIST_IMAGE";
}


