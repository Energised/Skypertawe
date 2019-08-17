/**
 * Account.java
 * @author Dan Woolsey
 *
 * Class to store user information as Account object
 */

// ~ OLD COMMENTS
//constructor example:
//BSTName.addAccount("Ricky3038", "Ricky", "Stephens", "123456", "17/01", "Swansea", 0, null, "pic.png");
//last 3 parameters may need to be null or zero if (no image set), (no new messages), and (new account so no last session).
//
// the profile picture is stored as a string of the filename:
//  1) "pic_name.png"

public class Account
{

    private String username;
	private String firstName;
	private String surname;
	private String mobileNumber;
	private String birthDate;
	private String city;
	private int numberNewMessages;
	private String lastSession;
	private String imgPath;


	//constructor
	public Account(String user, String first, String surname, String mobile,
                   String birth, String city, int newMessages,
                   String lastSession, String img)
    {
		this.username = user;
		this.firstName = first;
		this.surname = surname;
		this.mobileNumber = mobile;
		this.birthDate = birth;
		this.city = city;
		this.numberNewMessages = newMessages;
		this.lastSession = lastSession;
		this.imgPath = img;
	}

	// GETTERS AND SETTERS

	public void setUsername(String newUsername)
    {
		this.username = newUsername;
	}
	public String getUsername()
    {
		return this.username;
	}

	public void setFirstName(String newFirstname)
    {
		this.firstName = newFirstname;
	}
	public String getFirstName()
    {
		return this.firstName;
	}


	public void setSurname(String newSurname)
    {
		this.surname = newSurname;
	}
	public String getSurname()
    {
		return this.surname;
	}


	public void setMobNumber(String newMob)
    {
		this.mobileNumber = newMob;
	}
	public String getMobnumber()
    {
		return this.mobileNumber;
	}


	public void setBirthDate(String newBirthDate)
    {
		this.birthDate = newBirthDate;
	}
	public String getBirthDate()
    {
		return this.birthDate;
	}


	public void setCity(String newCity)
    {
		this.city = newCity;
	}
	public String getCity()
    {
		return this.city;
	}


	public void setNumNewMessages(int newMessages)
    {
		this.numberNewMessages = newMessages;
	}
	public int getNumNewMessages()
    {
		return this.numberNewMessages;
	}


	public void setLastSession(String newLastSession)
    {
		this.lastSession = newLastSession;
	}
	public String getLastSession()
    {
		return this.lastSession;
	}

	public void setImgPath(String newImgPath)
    {
		this.imgPath = newImgPath;
	}
	public String getImgPath()
    {
		return this.imgPath;
	}

    public static void main(String[] args)
    {
        Account a = new Account("Ricky3038", "Ricky", "Stephens", "123456", "17/01", "Swansea", 0, null, "pic.png");
        System.out.println(a.getFirstName());
        System.out.println(a.getSurname());
    }
}
