import { Injectable } from '@angular/core';
import { Comment } from '../model/Comment';
import axios from 'axios';

@Injectable()
export class CommentService {

  constructor() { }

  private postsUrl: string = '/api/posts';

  /**
   * Add a single new Comment to a Post.
   * @param {number} postId
   * @param {Comment} comment
   * @returns {Promise<void>}
   */
  saveComment(postId: number, comment: Comment): Promise<void> {
    return new Promise((resolve, reject) => {
      axios.post(`${this.postsUrl}/addcomment/${postId}`, comment)
        .then(() => resolve())
        .catch(err => reject(err));
    })
  }

  getPostComments(postId: number): Promise<Comment[]> {
    return new Promise((resolve, reject) => {
      axios.get(`${this.postsUrl}/getcomments/${postId}`)
        .then((res) => resolve(res.data.map(c => {return Comment.getInstance(c, res.data);})))
        .catch(err => reject(err));
    })
  }
}
