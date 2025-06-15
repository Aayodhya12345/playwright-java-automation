package com.utilities;


import com.microsoft.playwright.*;

public class BrowserUtils {

	public static class BrowserSession {
		public final Playwright playwright;
		public final Browser browser;
		public final BrowserContext context;
		public final Page page;

		public BrowserSession(Playwright playwright, Browser browser, BrowserContext context, Page page) {
			this.playwright = playwright;
			this.browser = browser;
			this.context = context;
			this.page = page;
		}
	}

	public static BrowserSession launchChromium(boolean headless) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
		return createSession(playwright, browser);
	}

	public static BrowserSession launchFirefox(boolean headless) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
		return createSession(playwright, browser);
	}

	public static BrowserSession launchWebkit(boolean headless) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
		return createSession(playwright, browser);
	}

	private static BrowserSession createSession(Playwright playwright, Browser browser) {
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		return new BrowserSession(playwright, browser, context, page);
	}
}


