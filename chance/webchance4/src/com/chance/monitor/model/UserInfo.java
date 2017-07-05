package com.chance.monitor.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "UserInfo")
public class UserInfo
{

    private int    UID;             //UID����¼ID
    private String nickName;        //�û��ǳ�
    private int    sex;             //��ݿ���������bit����(1���У�0��Ů)
    private long   Birthday;        //�û�����
    private String height;          //�û����
    private String constellation;   //�û�����
    private String bloodType;       //�û�Ѫ��
    private String nation;          //�û���
    private String city;            //����
    private String school;          //ѧУ
    private String interest;        //��Ȥ
    private String occupation;      //ְҵ
    private String state;           //����״̬(���?�ѻ飬������)
    private String realName;        //��ʵ����
    private String affiliation;     //��˾
    private String pic;             //ͷ��·��
    private String homePage;        //��ҳ
    private int    mood;            //����(����)
    private String status;          //����ǩ��
    private int    flag;            //������Ȩ��(Ĭ����ȫ��)
    private int    set_birthday;    //���ղ鿴Ȩ��
    private int    set_occupation;  //ְҵ�鿴Ȩ��
    private int    set_interest;    //��Ȥ�鿴Ȩ��
    private int    set_affiliation; //��˾�鿴Ȩ��
    private int    set_realname;    //��ʵ����鿴Ȩ��
    private int    set_state;       //����״̬�鿴Ȩ��
    private int    set_bloodtype;   //Ѫ�Ͳ鿴Ȩ��
    private int    set_shareMapAddr; //����λ�ò鿴Ȩ��

    /**
     * @param uID the uID to set
     */
    @XmlElement
    public void setUID(int uID)
    {
        UID = uID;
    }

    /**
     * @return the uID
     */
    public int getUID()
    {
        return UID;
    }

    /**
     * @param nickName the nickName to set
     */
    @XmlElement
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    /**
     * @return the nickName
     */
    public String getNickName()
    {
        return nickName;
    }

    /**
     * @param sex the sex to set
     */
    @XmlElement
    public void setSex(int sex)
    {
        this.sex = sex;
    }

    /**
     * @return the sex
     */
    public int getSex()
    {
        return sex;
    }

    /**
     * @param birthday the birthday to set
     */
    @XmlElement
    public void setBirthday(long birthday)
    {
        Birthday = birthday;
    }

    /**
     * @return the birthday
     */
    public long getBirthday()
    {
        return Birthday;
    }

    /**
     * @param height the height to set
     */
    @XmlElement
    public void setHeight(String height)
    {
        this.height = height;
    }

    /**
     * @return the height
     */
    public String getHeight()
    {
        return height;
    }

    /**
     * @param constellation the constellation to set
     */
    @XmlElement
    public void setConstellation(String constellation)
    {
        this.constellation = constellation;
    }

    /**
     * @return the constellation
     */
    public String getConstellation()
    {
        return constellation;
    }

    /**
     * @param bloodType the bloodType to set
     */
    @XmlElement
    public void setBloodType(String bloodType)
    {
        this.bloodType = bloodType;
    }

    /**
     * @return the bloodType
     */
    public String getBloodType()
    {
        return bloodType;
    }

    /**
     * @param nation the nation to set
     */
    @XmlElement
    public void setNation(String nation)
    {
        this.nation = nation;
    }

    /**
     * @return the nation
     */
    public String getNation()
    {
        return nation;
    }

    /**
     * @param interest the interest to set
     */
    @XmlElement
    public void setInterest(String interest)
    {
        this.interest = interest;
    }

    /**
     * @return the interest
     */
    public String getInterest()
    {
        return interest;
    }

    /**
     * @param occupation the occupation to set
     */
    @XmlElement
    public void setOccupation(String occupation)
    {
        this.occupation = occupation;
    }

    /**
     * @return the occupation
     */
    public String getOccupation()
    {
        return occupation;
    }

    /**
     * @param state the state to set
     */
    @XmlElement
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param realName the realName to set
     */
    @XmlElement
    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    /**
     * @return the realName
     */
    public String getRealName()
    {
        return realName;
    }

    /**
     * @param affiliation the affiliation to set
     */
    @XmlElement
    public void setAffiliation(String affiliation)
    {
        this.affiliation = affiliation;
    }

    /**
     * @return the affiliation
     */
    public String getAffiliation()
    {
        return affiliation;
    }

    /**
     * @param pic the pic to set
     */
    @XmlElement
    public void setPic(String pic)
    {
        this.pic = pic;
    }

    /**
     * @return the pic
     */
    public String getPic()
    {
        return pic;
    }

    public UserInfo()
    {
    }


    /**
     * Getter method for property <tt>city</tt>.
     * 
     * @return property value of city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Setter method for property <tt>city</tt>.
     * 
     * @param city value to be assigned to property city
     */
    @XmlElement
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
         * Getter method for property <tt>school</tt>.
         * 
         * @return property value of school
         */
    public String getSchool()
    {
        return school;
    }

    /**
     * Setter method for property <tt>school</tt>.
     * 
     * @param school value to be assigned to property school
     */
    @XmlElement
    public void setSchool(String school)
    {
        this.school = school;
    }

    /**
             * Getter method for property <tt>homePage</tt>.
             * 
             * @return property value of homePage
             */
    public String getHomePage()
    {
        return homePage;
    }

    /**
     * Setter method for property <tt>homePage</tt>.
     * 
     * @param homePage value to be assigned to property homePage
     */
    @XmlElement
    public void setHomePage(String homePage)
    {
        this.homePage = homePage;
    }

    /**
                 * Getter method for property <tt>mood</tt>.
                 * 
                 * @return property value of mood
                 */
    public int getMood()
    {
        return mood;
    }

    /**
     * Setter method for property <tt>mood</tt>.
     * 
     * @param mood value to be assigned to property mood
     */
    @XmlElement
    public void setMood(int mood)
    {
        this.mood = mood;
    }

    /**
                     * Getter method for property <tt>status</tt>.
                     * 
                     * @return property value of status
                     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     * 
     * @param status value to be assigned to property status
     */
    @XmlElement
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * Getter method for property <tt>flag</tt>.
     * 
     * @return property value of flag
     */
    public int getFlag()
    {
        return flag;
    }

    /**
     * Setter method for property <tt>flag</tt>.
     * 
     * @param flag value to be assigned to property flag
     */
    @XmlElement
    public void setFlag(int flag)
    {
        this.flag = flag;
    }

    /**
     * Getter method for property <tt>set_birthday</tt>.
     * 
     * @return property value of set_birthday
     */
    public int getSet_birthday()
    {
        return set_birthday;
    }

    /**
     * Setter method for property <tt>set_birthday</tt>.
     * 
     * @param set_birthday value to be assigned to property set_birthday
     */
    @XmlElement
    public void setSet_birthday(int set_birthday)
    {
        this.set_birthday = set_birthday;
    }

    /**
     * Getter method for property <tt>set_occupation</tt>.
     * 
     * @return property value of set_occupation
     */
    public int getSet_occupation()
    {
        return set_occupation;
    }

    /**
     * Setter method for property <tt>set_occupation</tt>.
     * 
     * @param set_occupation value to be assigned to property set_occupation
     */
    @XmlElement
    public void setSet_occupation(int set_occupation)
    {
        this.set_occupation = set_occupation;
    }

    /**
     * Getter method for property <tt>set_interest</tt>.
     * 
     * @return property value of set_interest
     */
    public int getGet_interest()
    {
        return set_interest;
    }

    /**
     * Setter method for property <tt>set_interest</tt>.
     * 
     * @param set_interest value to be assigned to property set_interest
     */
    @XmlElement
    public void setSet_interest(int set_interest)
    {
        this.set_interest = set_interest;
    }

    /**
     * Getter method for property <tt>set_affiliation</tt>.
     * 
     * @return property value of set_affiliation
     */
    public int getSet_affiliation()
    {
        return set_affiliation;
    }

    /**
     * Setter method for property <tt>set_affiliation</tt>.
     * 
     * @param set_affiliation value to be assigned to property set_affiliation
     */
    @XmlElement
    public void setSet_affiliation(int set_affiliation)
    {
        this.set_affiliation = set_affiliation;
    }

    /**
     * Getter method for property <tt>set_realname</tt>.
     * 
     * @return property value of set_realname
     */
    public int getSet_realname()
    {
        return set_realname;
    }

    /**
     * Setter method for property <tt>set_realname</tt>.
     * 
     * @param set_realname value to be assigned to property set_realname
     */
    @XmlElement
    public void setSet_realname(int set_realname)
    {
        this.set_realname = set_realname;
    }

    /**
     * Getter method for property <tt>set_state</tt>.
     * 
     * @return property value of set_state
     */
    public int getSet_state()
    {
        return set_state;
    }

    /**
     * Setter method for property <tt>set_state</tt>.
     * 
     * @param set_state value to be assigned to property set_state
     */
    @XmlElement
    public void setSet_state(int set_state)
    {
        this.set_state = set_state;
    }

    /**
     * Getter method for property <tt>set_bloodtype</tt>.
     * 
     * @return property value of set_bloodtype
     */
    public int getSet_bloodtype()
    {
        return set_bloodtype;
    }

    /**
     * Setter method for property <tt>set_bloodtype</tt>.
     * 
     * @param set_bloodtype value to be assigned to property set_bloodtype
     */
    @XmlElement
    public void setSet_bloodtype(int set_bloodtype)
    {
        this.set_bloodtype = set_bloodtype;
    }

    public int getSet_shareMapAddr()
    {
        return set_shareMapAddr;
    }
    @XmlElement
    public void setSet_shareMapAddr(int set_shareMapAddr)
    {
        this.set_shareMapAddr = set_shareMapAddr;
    }
}
