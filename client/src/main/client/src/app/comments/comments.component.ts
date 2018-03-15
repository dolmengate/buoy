import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../model/Comment';
import {PostService} from "../post.service";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  // todo: CommentService

  @Input()
  public postId: number;

  @Input()
  public comments: Comment[];

  public parentComment: Comment = null;
  public composedComment: Comment = new Comment();
  public commentForm: boolean = false;

  constructor(private postService: PostService) { }

  ngOnInit() { }

  toggleCommentForm() {
    this.commentForm = !this.commentForm;
  }

  setParentComment(commentId: number) {
    this.parentComment = this.comments.find(c => c.commentId === commentId);
    this.composedComment.replyToId = this.parentComment.commentId;
  }

  clearParentComment() {
    this.parentComment = null;
  }

  addComment() {
    if (this.parentComment)
        this.parentComment.replies.push(this.composedComment);
    this.comments.push(this.composedComment);

    this.postService.savePostComment(this.postId, this.composedComment);

    // reset
    this.composedComment = new Comment();
    this.parentComment = null;
  }
}
