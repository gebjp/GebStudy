package jp.org.gebjp.util;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;

public class GebCookieUtil {

  private Options options = null;

  public GebCookieUtil(WebDriver driver) {
    this.options = driver.manage();
  }

  /**
   * Cookiesコンソールに出力します
   */
  public void printCookies() {
    System.out.println("--start getCookies-----");
    for (Cookie cookie : options.getCookies()) {
      System.out.println(String.format("key: %s, value: %s", cookie.getName(), cookie.getValue()));
    }
    System.out.println("--end getCookies-----");
  }

  /**
   * クッキーから値を取得します
   * 
   * @param key
   * @return
   */
  public String getCookieVal(String key) {
    String result = null;
    for (Cookie cookie : options.getCookies()) {
      if (cookie.getName().equals(key)) {
        result = cookie.getValue();
        break;
      }
    }
    return result;
  }

  /**
   * クッキーに値をセットします
   * 
   * @param key
   * @param val
   */
  public void setCookieVal(String key, String val) {
    options.addCookie(new Cookie(key, val));
  }
}
