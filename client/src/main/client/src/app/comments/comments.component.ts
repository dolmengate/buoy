import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../model/Comment';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  @Input()
  public comments: Comment[];

  public activeComment: Comment;

  // get post

  constructor() { }

  ngOnInit() { }

  appendReplyId(commentId: number) {
    document.getElementById('comment-area').innerText += "@" + commentId + '\n';
    this.activeComment = this.comments.find(c => c.commentId === commentId);
  }

  addComment() {
  }
}
