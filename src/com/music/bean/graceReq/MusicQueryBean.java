
package com.music.bean.graceReq;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class MusicQueryBean{
    
    public MusicQueryBean() {}

    private String cmd;
    private String mode;
    private List<TextParamBean> textParamList;
    private List<OptionParamBean> optionParamList;
    private RangeBean rangeBean;
    
    @XmlAttribute(name="CMD")
    public String getCmd() {
    	return this.cmd;
    }

    public void setCmd(String cmd) {
    	this.cmd = cmd;
    }

    @XmlElement(name="MODE")
    public String getMode() {
    	return this.mode;
    }

    public void setMode(String mode) {
    	this.mode = mode;
    }
    
    @XmlElement(name="TEXT")
    public List<TextParamBean> getTextParamList() {
    	return this.textParamList;
    }

    public void setTextParamList(List<TextParamBean> textParamList) {
    	this.textParamList = textParamList;
    }
    

    @XmlElement(name="OPTION")
    public List<OptionParamBean> getOptionParamList() {
    	return this.optionParamList;
    }

    public void setOptionParamList(List<OptionParamBean> optionParamList) {
    	this.optionParamList = optionParamList;
    }
    
    @XmlElement(name="RANGE")
    public RangeBean getRangeBean() {
    	return this.rangeBean;
    }

    public void setRangeBean(RangeBean rangeBean) {
    	this.rangeBean = rangeBean;
    }
    

}
