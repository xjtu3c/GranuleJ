package granulej.gui.datastructure;

public class GranuleUnit{
	public Class simiGranule=null;
	public Class currentGranule=null;
	public String simiFileName="non-existent";
	
	public GranuleUnit(Class simiGranule,Class currentGranule,String simiFileName)
	{
		this.simiFileName=simiFileName;
		this.simiGranule=simiGranule;
		this.currentGranule=currentGranule;
	}
}
