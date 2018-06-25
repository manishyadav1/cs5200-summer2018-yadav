package edu.northeastern.cs5200;

import edu.northeastern.cs5200.query.create.developer.CreateDeveloper;
import edu.northeastern.cs5200.query.create.page.CreatePage;
import edu.northeastern.cs5200.query.create.priviledge.CreatePagePriviledge;
import edu.northeastern.cs5200.query.create.priviledge.CreateWebsitePriviledge;
import edu.northeastern.cs5200.query.create.role.CreatePageRole;
import edu.northeastern.cs5200.query.create.role.CreateWebsiteRole;
import edu.northeastern.cs5200.query.create.website.CreateWebsite;
import edu.northeastern.cs5200.query.create.widget.CreateWidget;
import edu.northeastern.cs5200.query.delete.Delete;
import edu.northeastern.cs5200.query.update.Update;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Cs5200Summer2018YadavApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cs5200Summer2018YadavApplication.class, args);

        CreateDeveloper.createDeveloper();

        CreateWebsite.createWebsite();

        CreateWebsiteRole.createWebsiteRole();

        CreateWebsitePriviledge.createWebsitePriviledge();

        CreatePage.createPage();

        CreatePageRole.createPageRole();

        CreatePagePriviledge.createPagePriviledge();

        CreateWidget.createWidget();

        Update.updateWidget();

        Update.updatePage();

        Update.updateRole();

        Delete.deleteWidget();

        Delete.deletePage();

        Delete.deleteWebsite();
    }
}
