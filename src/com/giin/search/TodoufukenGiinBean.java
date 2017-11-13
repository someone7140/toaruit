
package com.giin.search;

import java.util.List;

public class TodoufukenGiinBean {

    private String todouhukenId;
    private String todouhukenName;
    private List<SyuugiinBean> syuugiinList;
    private List<SangiinBean> sangiinList;
    private List<ChijiBean> chijiList;
    
    public TodoufukenGiinBean() {}

    public String getTodouhukenId() {
    	return this.todouhukenId;
    }

    public void setTodouhukenId(String todouhukenId) {
    	this.todouhukenId = todouhukenId;
    }
    
    public String getTodouhukenName() {
    	return this.todouhukenName;
    }

    public void setTodouhukenName(String todouhukenName) {
    	this.todouhukenName = todouhukenName;
    }
    
    public List<SyuugiinBean> getSyuugiinList() {
    	return this.syuugiinList;
    }

    public void setSyuugiinList(List<SyuugiinBean> syuugiinList) {
    	this.syuugiinList = syuugiinList;
    }
    
    public List<SangiinBean> getSangiinList() {
    	return this.sangiinList;
    }

    public void setSangiinList(List<SangiinBean> sangiinList) {
    	this.sangiinList = sangiinList;
    }
    
    
    public List<ChijiBean> getChijiList() {
    	return this.chijiList;
    }

    public void setChijiList(List<ChijiBean> chijiList) {
    	this.chijiList = chijiList;
    }
    
}
