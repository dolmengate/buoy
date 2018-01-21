import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { EditorComponent } from './editor/editor.component';
import {StompConfig, StompService} from "@stomp/ng2-stompjs";
import * as SockJS from "sockjs-client";

export function socketProvider() {
  return new SockJS('/buoy');
}

const stompConfig: StompConfig = {
  url: socketProvider,
  headers: {},
  heartbeat_in: 0,
  heartbeat_out: 0,
  reconnect_delay: 5000,
  debug: false
};

@NgModule({
  declarations: [
    AppComponent,
    EditorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
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
