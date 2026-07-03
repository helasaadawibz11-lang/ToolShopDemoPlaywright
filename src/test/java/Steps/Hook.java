package Steps;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hook {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    // Cette méthode permet de récupérer la page active dans tes fichiers de Steps
    public static Page getPage() {
        return page;
    }

    @Before
    public void setUp() {
        // 1. Initialiser Playwright et lancer le navigateur (ici Chromium/Chrome)
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false) // Mettre à true pour l'exécuter en arrière-plan
                .setSlowMo(100));   // Ralentit un peu les actions pour pouvoir observer le test

        // 2. Créer un contexte de navigation et une nouvelle page
        context = browser.newContext();
        page = context.newPage();
    }

    @After
    public void tearDown(Scenario scenario) {
        // 3. Prendre une capture d'écran automatique dans le rapport Cucumber si le test échoue
        if (scenario.isFailed() && page != null) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(screenshot, "image/png", "Capture d'écran de l'échec");
        }

        // 4. Nettoyer et fermer proprement les instances Playwright
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}