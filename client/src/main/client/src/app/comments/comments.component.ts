import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../model/Comment';
import {CommentService} from "../services/comment.service";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  @Input()
  public postId: number;

  @Input()
  public comments: Comment[];

  public parentComment: Comment = null;
  public composedComment: Comment = new Comment();
  public commentForm: boolean = false;

  constructor(private commentService: CommentService) { }

  ngOnInit() { }

  toggleCommentForm() {
    this.commentForm = !this.commentForm;
  }

  setParentComment(commentId: number) {
    this.parentComment = this.comments.find(c => c.commentId === commentId);
    this.composedComment.replyToId = this.parentComment.commentId;
    this.commentForm = true;
  }

  clearParentComment() {
    this.parentComment = null;
  }

  addComment() {
    this.commentService.saveComment(this.postId, this.composedComment)
      .then(() => { location.reload(true); } );

    // reset
    this.composedComment = new Comment();
    this.clearParentComment();
  }
}
