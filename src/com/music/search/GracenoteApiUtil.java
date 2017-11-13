package com.music.search;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.music.bean.RequestMusicBean;
import com.music.bean.ResponseMusicBean;
import com.music.bean.ResultMusicBean;
import com.music.bean.graceReq.AuthBean;
import com.music.bean.graceReq.MusicQueryBean;
import com.music.bean.graceReq.OptionParamBean;
import com.music.bean.graceReq.RangeBean;
import com.music.bean.graceReq.TextParamBean;
import com.music.bean.graceRes.TrackMoodBean;
import com.music.bean.graceRes.TrackTempoBean;
import com.music.bean.graceRes.UrlTypeBean;

public class GracenoteApiUtil {
	
    /*
     *ユーザー登録のAPIでユーザIDを出力

    public static void outGracenoteUserId(){
    	// クライアントID文字列のオブジェジェクト
    	ClientBean clientBean = new ClientBean();
    	// クライアントIDの設定
    	clientBean.setClientId(MusicConstants.GRACENOTE_CLIENT_ID);
    	// コマンドの設定
    	clientBean.setCmd(MusicConstants.USER_REISTER_CMD);
    	// リクエスト用Beanに格納
    	RequestUserBean requestUserBean =new RequestUserBean();
    	requestUserBean.setClientBean(clientBean);
    	// オブジェクトを文字列化
    	String requestXML = MusicUtil.getXMLToString(requestUserBean);
    	// リクエスト送信
    	String userId = MusicUtil.getPostResult(requestXML);
    	// とりあえず標準出力
    	System.out.println(userId);
    }
    */
    /*
     *曲名検索のrequest
    */
    public static String getGracenoteMusicXml(String searchArtist ,String searchTrack ,String searchAlbum, HttpServletRequest request){
    	// リクエストからコンテキストを取得
        ServletContext context = request.getSession().getServletContext();
    	// リクエスト用のオブジェクト
    	RequestMusicBean requestMusicBean = new RequestMusicBean();
    	// AuthBeanの設定
    	AuthBean authbean = new AuthBean();
    	authbean.setClientId(context.getInitParameter("GRACENOTE_CLIENT_ID"));
    	authbean.setUserId(context.getInitParameter("GRACENOTE_USER_ID"));
    	requestMusicBean.setAuthBean(authbean);
    	// Langの設定
    	requestMusicBean.setLang(MusicConstants.LANG_JPN);
    	// Queryの設定
    	MusicQueryBean musicQueryBean = new MusicQueryBean();
    	// コマンドの設定
    	musicQueryBean.setCmd(MusicConstants.ALBUM_SEARCH_CMD);
    	// テキストタイプのリスト設定
    	List<TextParamBean> textParamList = new ArrayList<TextParamBean>();
    	// アーティストの設定
    	if(!MusicUtil.isEmpty(searchArtist) ){
        	TextParamBean textParamBeanArtist = new TextParamBean();
        	textParamBeanArtist.setType(MusicConstants.ARTIST_TYPE);
        	textParamBeanArtist.setText(searchArtist);
        	textParamList.add(textParamBeanArtist);
    	}
    	// 曲名の設定
    	if(!MusicUtil.isEmpty(searchTrack) ){
        	TextParamBean textParamBeanTrack = new TextParamBean();
        	textParamBeanTrack.setType(MusicConstants.TRACK_TITLE_TYPE);
        	textParamBeanTrack.setText(searchTrack);
        	textParamList.add(textParamBeanTrack);
    	}
    	// アルバム名の設定
    	if(!MusicUtil.isEmpty(searchAlbum) ){
        	TextParamBean textParamBeanAlbum = new TextParamBean();
        	textParamBeanAlbum.setType(MusicConstants.ALBUM_TYPE);
        	textParamBeanAlbum.setText(searchAlbum);
        	textParamList.add(textParamBeanAlbum);
    	}
    	musicQueryBean.setTextParamList(textParamList);
    	// モードの設定
    	musicQueryBean.setMode(MusicConstants.MODE_SINGLE_BEST_COVER);
    	// オプションのリスト設定
    	List<OptionParamBean> optionParamList = new ArrayList<OptionParamBean>();
    	// EXTENDのパラメータ設定
    	OptionParamBean optionExtend = new OptionParamBean();
    	optionExtend.setParameter(MusicConstants.OPTION_EXTENDED);
    	optionExtend.setValue(MusicConstants.OPTION_EXTENDED_VALUE);
    	optionParamList.add(optionExtend);
    	// DETAILのパラメータ設定
    	OptionParamBean optionDetail = new OptionParamBean();
    	optionDetail.setParameter(MusicConstants.OPTION_DETAIL);
    	optionDetail.setValue(MusicConstants.OPTION_DETAIL_VALUE);
    	optionParamList.add(optionDetail);
    	musicQueryBean.setOptionParamList(optionParamList);
    	// Rangeを設定
    	RangeBean rangeBean = new RangeBean();
    	rangeBean.setStartRange("1");
    	rangeBean.setEndRange("10");
    	musicQueryBean.setRangeBean(rangeBean);
    	// musicQueryBeanをリクエスト用のBeanに設定
    	requestMusicBean.setMusicQueryBean(musicQueryBean);
    	// オブジェクトを文字列化
    	String requestXML = MusicUtil.getXMLToString(requestMusicBean);
    	// リクエスト送信
    	String responseXML = MusicUtil.getPostResult(requestXML);
    	// XMLの文字列を返す
    	return responseXML;
    }
    
    /*
     *Gracenoteのレスポンスオブジェクトを結果オブジェクトにして返す
    */
    
    public static ResultMusicBean getGracenoteResultObj(ResponseMusicBean responseMusicBean){
    	// 結果用オブジェクト
    	ResultMusicBean resultMusicBean = new ResultMusicBean();
    	
    	// 曲名のセット
    	resultMusicBean.setTitle(responseMusicBean.getResponseMusicChildBean().getAlbumBean().getTrack().getTitle());
    	// アーティスト名のセット
    	resultMusicBean.setArtistName(responseMusicBean.getResponseMusicChildBean().getAlbumBean().getArtistName());
    	// アーティストイメージURLのセット
    	List<UrlTypeBean> urlList = responseMusicBean.getResponseMusicChildBean().getAlbumBean().getUrlTypeBeanList();
    	for (UrlTypeBean urlTypeBean : urlList) {
    		// URLBeanの属性がARTIST_IMAGE
    		if(urlTypeBean.getType().equals(MusicConstants.RESPONSE_URL_ARTIST_IMAGE_ATTR)){
    			// 結果用オブジェクトにセット
    			resultMusicBean.setArtistImage(urlTypeBean.getUrlText());
    		}
    	}
    	
    	// 雰囲気のセット
    	List <String> moodTextList = new ArrayList<String>();
    	List<TrackMoodBean> moodList = responseMusicBean.getResponseMusicChildBean().getAlbumBean().getTrack().getTrackMoodList();
    	for (TrackMoodBean moodBean : moodList) {
    		// beanからtext情報を取得しリストに格納
    		moodTextList.add(moodBean.getMoodText());
    	}
    	// 結果用オブジェクトにリストを格納
    	resultMusicBean.setMoodList(moodTextList);
    	
    	// テンポのセット
    	List <String> tempoTextList = new ArrayList<String>();
    	List<TrackTempoBean> tempoList = responseMusicBean.getResponseMusicChildBean().getAlbumBean().getTrack().getTrackTempoList();
    	for (TrackTempoBean tempoBean : tempoList) {
    		// beanからtext情報を取得しリストに格納
    		tempoTextList.add(tempoBean.getTempoText());
    	}
    	// 結果用オブジェクトにリストを格納
    	resultMusicBean.setTempoList(tempoTextList);
    	//オブジェクトを返す
    	return resultMusicBean;
    	
    }
    
}
