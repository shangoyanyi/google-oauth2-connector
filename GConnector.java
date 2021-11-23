public class GConnector {
  private String clientId = "<YOUR_CLIENT_ID>";	// TODO: place your clientID here
  private String clientSecret = "<YOUR_CLIENT_SECRET>";	// TODO: place your client secret here
  private String redirectUri = "<YOUR_REDIRECT_URI>"; // TODO: place your callback url here
  private String scope = ""; 
  private String[] GOOGLE_DEFAULT_SCOPES = {
    "https://www.googleapis.com/auth/plus.me",  
    "https://www.googleapis.com/auth/userinfo.profile",
    "https://www.googleapis.com/auth/userinfo.email"
  }; // TODO: place all scopes you need here
	
  /**
   * constructor.
   */
  public GConnector(){
    for(int i=0; i<GOOGLE_DEFAULT_SCOPES.length; i++){
      scope += GOOGLE_DEFAULT_SCOPES[i]+" ";
    }
  }
	
  /** 
   * step1. 取得轉導到 google 認證的 url 
   */
  public String getRedirectUrl(String state){
    String url = "https://accounts.google.com/o/oauth2/auth";
    String query = "?client_id=" + this.clientId + 
			"&scope=" + this.scope +
			"&redirect_uri=" + this.redirectUri +
			"&response_type=code" +
			"&approval_prompt=auto" + 
			"&state=" + state;
		
    return url + query;
  }

  /**
   * step2. 取得將 code 轉換成 access_token 的 url (透過server-side發送request)
   */
  public String getAuthTokenUrl(String code){
    String url = "https://oauth2.googleapis.com/token";
    String query = "?code=" + code + 
			"&client_id=" + this.clientId +
			"&client_secret=" + this.clientSecret  +
			"&redirect_uri=" + this.redirectUri +
			"&grant_type=authorization_code" ;
    
    return url + query;
  }
  
  /**
   * step3. 取得確認 access_token 的 url (透過server-side發送request)
   */
  public String getTokenInfoUrl(String accessToken){
    String url = "https://www.googleapis.com/oauth2/v1/tokeninfo";
    String query = "?access_token=" + accessToken;
    
    return url + query;
  }  
  
  /**
   * step4. 取得 user_info 的 url (透過server-side發送request)
   */
  public String getUserInfoUrl(String accessToken){
    String url = "https://www.googleapis.com/oauth2/v1/userinfo";
    String query = "?access_token=" + accessToken;
    
    return url + query;
  }  
}
