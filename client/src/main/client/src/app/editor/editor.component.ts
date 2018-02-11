import {Component, Input, OnDestroy, OnInit} from '@angular/core';
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

  @Input() postId: number;
  private editorId: number;
  private freshConnect: boolean = true;
  public editorText: string;
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
      // get initial editor content
      this.sendMessage();
      this.freshConnect = false;
    }, 2000);
  }

  ngOnDestroy() {
    this.stompSubscription.unsubscribe();
  }

  onMessageReceived = (frame: Message) => {
    const res = JSON.parse(frame.body);

    // only update this editor if the message is for it
    if (res.postId === this.postId) {
      this.editorText = res.text;
      this.editorId = res.editorId; // server prefers editorId for editor lookup
    }
    console.log('received', res);
  };

  sendMessage() {
    this.stompService.publish(
      '/app/message',
      JSON.stringify({ freshConnect: this.freshConnect, text: this.editorText, postId: this.postId, editorId: this.editorId } )
    );
    console.log('sending',{ freshConnect: this.freshConnect, text: this.editorText, postId: this.postId, editorId: this.editorId } );
  }

  handleTextareaKeyDown(event: KeyboardEvent) {
  event.preventDefault();
  document.execCommand('insertText', null, '    '); // spaces instead of \t for browser compatibility
  }

  handleTextareaKeyUp() {
    this.sendMessage();     // send the contents of editorText to update all subscribers
  }
}
