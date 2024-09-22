public class Provider 
{
    private String providerName;
    private int countryCode;
    private int areaCode;
    private int phoneNumber;
    
    public Provider(String Name, int CountryCode, int AreaCode, int PhoneNumber)
    {
        setName(Name);
        setCountry(CountryCode);
        setArea(AreaCode);
        setPhone(PhoneNumber);
    }

    public void setName(String name)
    {
        providerName = name;
    }

    public String getName()
    {
        return providerName;
    } 

    public void setArea(int code)
    {
        areaCode = code;
    }

    public int getArea()
    {
        return areaCode;
    } 

    public void setCountry(int code)
    {
        countryCode = code;
    }

    public int getCountry()
    {
        return countryCode;
    } 

    public void setPhone(int number)
    {
        phoneNumber = number;
    }

    public int getPhone()
    {
        return phoneNumber;
    } 

}   
