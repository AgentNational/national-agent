
package com.pay.national.agent.model.entity;

import java.io.Serializable;

public class SoftwareVersionInfo implements Comparable<SoftwareVersionInfo>,Serializable {

	private static final long serialVersionUID = 6139956240138342219L;
	/**软件的主版本号*/
	private int major;
	/**软件的次版本号*/
	private int minor;
	/**软件的修复版本号*/
	private int build;
	/**软件的版本号*/
	private String version;
	/**前者大*/
	public static final int FORMER_IS_BIG = -1;
	/**两者相等*/
	public static final int TWO_IS_EQUAL = 0;
	/**后者大*/
	public static final int LATTER_IS_BIG = 1;
	/**不能识别*/
	public static final int UNRECOGNIZED = -100;

	public SoftwareVersionInfo(String version)throws Exception{
		if(version == null)
			throw new Exception("version can not be null");
		String [] vers = version.split("\\.") ;
		if(vers.length == 0)
			throw new Exception("version format error");
		else
			this.major = Integer.parseInt(vers[0]) ;

		if(vers.length == 3){
			this.minor = Integer.parseInt(vers[1]) ;
			this.build = Integer.parseInt(vers[2]) ;
		} else if(vers.length == 2){
			this.minor = Integer.parseInt(vers[1]) ;
			this.build = 0 ;
		} else if(vers.length == 1){
			this.minor = 0 ;
			this.build = 0 ;
		}
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public int getBuild() {
		return build;
	}

	public void setBuild(int build) {
		this.build = build;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public int compareTo(SoftwareVersionInfo o) {
		if(this == o)
			return SoftwareVersionInfo.TWO_IS_EQUAL;
		else if (o != null && o instanceof SoftwareVersionInfo) {
			SoftwareVersionInfo u = (SoftwareVersionInfo) o;

            if(this.major == u.major){
            	if(this.minor == u.minor){
            		if(this.build == u.build){
                    	return SoftwareVersionInfo.TWO_IS_EQUAL;
        	        } else if(this.build > u.build){
                        return SoftwareVersionInfo.FORMER_IS_BIG;
                    } else {
                    	return SoftwareVersionInfo.LATTER_IS_BIG;
                    }
    	        } else if(this.minor > u.minor){
                    return SoftwareVersionInfo.FORMER_IS_BIG;
                } else {
                	return SoftwareVersionInfo.LATTER_IS_BIG;
                }
	        } else if(this.major > u.major){
                return SoftwareVersionInfo.FORMER_IS_BIG;
            } else {
				return SoftwareVersionInfo.LATTER_IS_BIG;
			}
		}else {
			return SoftwareVersionInfo.UNRECOGNIZED ;
		}
	}

	public boolean equals(Object o) {
		if (!(o instanceof SoftwareVersionInfo)) {
			return false;
		}

		SoftwareVersionInfo xx = (SoftwareVersionInfo) o;

		return  ( this.major == xx.major ) &&
				( this.minor == xx.minor ) &&
				( this.build == xx.build );
	}

	@Override
	public String toString() {
		return "SoftwareVersionInfo [major=" + major + ", minor=" + minor
				+ ", build=" + build + ", version=" + version + "]";
	}

	public static void main(String[] args) {
		try {
			SoftwareVersionInfo s1 = new SoftwareVersionInfo("2.1.1") ;
			SoftwareVersionInfo s2 = new SoftwareVersionInfo("2.1.2") ;

			System.out.println(s1.compareTo(s2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

