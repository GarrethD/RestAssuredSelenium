package PageObjects.FrontEndPageObjects;

public class PageObjects {

    public static String BookStoreApplicationButton()
    {
        return "//h5[text()='Book Store Application']";
    }
    public static String BookStoreLandingPageHeader()
    {
        return "//div[text()='Book Store']";
    }
    public static String LoginButton()
    {
        return "//button[@id='login']";
    }
    public static String LoginToBookStoreLandingPageHeader()
    {
        return "//h5[text()='Login in Book Store']";
    }
    public static String LoginUsernameField()
    {
        return "//input[@id='userName']";
    }
    public static String LoginPasswordField()
    {
        return "//input[@id='password']";
    }
//======================================================Adding and validating books to collection==========================================================
public static String BookTitleLink(String BookTitle)
{
    return "//a[text()=\""+BookTitle+"\"]";
}
    public static String TitleOfBook(String BookTitle)
    {
        return "//label[@id='title-label']/../..//label[text()=\""+BookTitle+"\"]";
    }
    public static String AddToYourCollectionButton()
    {
        return "//button[@id='addNewRecordButton'][text()='Add To Your Collection']";
    }


    //====================================================Iframes======================================================
    public static String DeleteBookPopupIframe()
    {
        return "//button[@id='submit'][text()='Log out']";
    }

    //=================================================================================================================
    public static String LogoutButton()
    {
        return "//button[@id='submit'][text()='Log out']";
    }
    public static String ProfileButton()
    {
        return "//span[text()='Profile']";
    }
    public static String ProfilePageHeader()
    {
        return "//div[text()='Profile']";
    }
    public static String DeleteBookTrashCanButton(String BookTitle)
    {
        return "//a[text()=\""+BookTitle+"\"]/../../../..//span[@id='delete-record-undefined']";
    }
    public static String DeleteBookOkButton()
    {
        return "//button[@id='closeSmallModal-ok']";
    }
    public static String NoRowDataText()
    {
        return "//div[@class='rt-noData']";
    }

}
