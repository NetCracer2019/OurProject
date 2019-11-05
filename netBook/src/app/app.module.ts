import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule} from "@angular/router";
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MenuComponent } from './menu/menu.component';
import { ContentMainComponent } from './content-main/content-main.component';
import { ContentBookComponent } from './content-book/content-book.component';
import { ContentProfileComponent } from './content-profile/content-profile.component';
import { ContentFriendsComponent } from './content-friends/content-friends.component';
import { ContentRecommendationsComponent } from './content-recommendations/content-recommendations.component';
import { ContentChatComponent } from './content-chat/content-chat.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import {FormsModule} from "@angular/forms";
import { MainComponent } from './main/main.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MenuComponent,
    ContentMainComponent,
    ContentBookComponent,
    ContentProfileComponent,
    ContentFriendsComponent,
    ContentRecommendationsComponent,
    ContentChatComponent,
    WelcomePageComponent,
    MainComponent,
    RegistrationComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: 'books',
        component: ContentBookComponent
      },
      {
        path: 'profile',
        component: ContentProfileComponent
      },
      {
        path: 'friends',
        component: ContentFriendsComponent
      },
      {
        path: 'recommendations',
        component: ContentRecommendationsComponent
      },
      {
        path: 'chat',
        component: ContentChatComponent
      },
      {
        path: '', redirectTo: 'home', pathMatch: 'full'
      },
      {
        path: 'home',
        component: ContentMainComponent
      },

    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
