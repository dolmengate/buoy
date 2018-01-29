import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NavigationComponent } from "./navigation/navigation.component";

import { AppComponent } from './app.component';
import { EditorComponent } from './editor/editor.component';
import { IndexComponent } from "./index/index.component";
import { PostPageComponent } from "./post-page/post-page.component";

import { StompConfig, StompService } from "@stomp/ng2-stompjs";
import * as SockJS from "sockjs-client";
import {NewPostComponent} from "./new-post/new-post.component";

const stompConfig: StompConfig = {
  url: () => new SockJS('/buoy'),
  headers: {},
  heartbeat_in: 0,
  heartbeat_out: 0,
  reconnect_delay: 5000,
  debug: false
};

// Route order matters
const appRoutes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'posts/:id', component: PostPageComponent },
  { path: 'newpost', component: NewPostComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    EditorComponent,
    NavigationComponent,
    PostPageComponent,
    NewPostComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false }
    )
  ],
  providers: [
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
