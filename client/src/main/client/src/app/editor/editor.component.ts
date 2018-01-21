import { Component, OnInit } from '@angular/core';
import {StompService} from "@stomp/ng2-stompjs";
import {Message} from "@stomp/stompjs"
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  private freshConnect: boolean = true;
  private editorText: string;
  // private stompSubscription: Subscription;  // to unsubscribe
  public messages: Observable<Message>;

  constructor(private stompService: StompService) { }

  ngOnInit() {
    this.messages =   // messages will watch for and 'mirror' anything published to '/topic/editor'
      this.stompService.subscribe('/topic/editor');

    // this.stompSubscription =
    // instruct messages Observable what to do each time it detects a message from its subscription ('/topic/editor')
    this.messages.subscribe(this.onMessageReceived);
    this.sendMessage();
    this.freshConnect = false;
  }

  onMessageReceived = (frame: Message) => {
    this.editorText = JSON.parse(frame.body).text;  // update property editorText
    console.log(frame);
  };

  sendMessage() {
    this.stompService.publish(
      '/app/message',
      JSON.stringify({ freshConnect: this.freshConnect, text: this.editorText } )
    );
    console.log({ freshConnect: this.freshConnect, text: this.editorText });
  }

  handleTextareaKeyDown(event) {
    if (event.key === 'Tab') {
      event.preventDefault();
      document.execCommand('insertText', null, '    '); // spaces instead of \t for browser compatibility
    }
  }

  handleTextareaKeyUp() {
    this.sendMessage();     // send the contents of editorText to update all subscribers
  }
}
