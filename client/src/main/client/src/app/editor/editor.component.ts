import {Component, OnDestroy, OnInit} from '@angular/core';
import {StompService} from "@stomp/ng2-stompjs";
import {Message} from "@stomp/stompjs"
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit, OnDestroy {

  private freshConnect: boolean = true;
  private editorText: string;
  private stompSubscription: Subscription;  // to unsubscribe
  public messages: Observable<Message>;

  constructor(private stompService: StompService) { }

  ngOnInit() {
    this.messages =   // messages will watch for and 'mirror' anything published to '/topic/editor'
      this.stompService.subscribe('/topic/editor');

    // instruct messages Observable what to do each time it detects a message from its subscription ('/topic/editor')
    this.stompSubscription = this.messages.subscribe(this.onMessageReceived);
    // fixme: this sucks - wait for the connection to be established before sending the first message
    setTimeout(() => {
      this.sendMessage();
      this.freshConnect = false;
    }, 2000);
  }

  ngOnDestroy() {
    this.stompSubscription.unsubscribe();
  }

  onMessageReceived = (frame: Message) => {
    this.editorText = JSON.parse(frame.body).text;  // update property editorText
    console.log('received', frame.body);
  };

  sendMessage() {
    this.stompService.publish(
      '/app/message',
      JSON.stringify({ freshConnect: this.freshConnect, text: this.editorText } )
    );
    console.log('sending', { freshConnect: this.freshConnect, text: this.editorText });
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
