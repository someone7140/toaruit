

package com.twitter.search;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TwitterConstants {

    // 集計結果表示区分ワード別
	public static final String SEARCHKIND_WORD = "word";
	
    // 集計除外句
	public static final List<String> EXCEPT_TARGET = Collections.unmodifiableList(Arrays.asList("助詞", "助動詞","記号","フィラー"));

}


