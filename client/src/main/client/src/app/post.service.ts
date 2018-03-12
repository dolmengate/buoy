import { Injectable } from '@angular/core';
import {Post} from "./model/Post";
import {Comment} from "./model/Comment";
import axios from "axios";

@Injectable()
export class PostService {

  private postsUrl: string = '/api/posts';

  constructor() { }

  getPosts(): Promise<Post[]> {
    return new Promise((resolve, reject) => {
      axios.get(this.postsUrl)
        .then(res => resolve(res.data.map(p => {return Post.getInstance(p)})))
        .catch(err => { reject(err);});
    });
  }

  getPost(postId: number): Promise<Post> {
    return new Promise((resolve, reject) => {
      const singlePostUrl = `${this.postsUrl}/${postId}`;
      axios.get(singlePostUrl)
        .then(res => {resolve(Post.getInstance(res.data)); })
        .catch(err => reject(err));
    });
  }

  savePost(post: Post): Promise<void> {
    return new Promise((resolve, reject) => {
      const savePostUrl = `${this.postsUrl}/save/${post.postId}`;
      axios.post(savePostUrl, post)
        .then(() => resolve())
        .catch((err) => reject(err));
    })
  }

  newPost(post: Post): Promise<void> {
    return new Promise((resolve, reject) => {
      axios.post(`${this.postsUrl}/new`, post)
        .then(() => resolve())
        .catch((err) => reject(err));
    })
  }

  savePostComment(postId: number, comment: Comment): Promise<void> {
    return new Promise((resolve, reject) => {
      axios.post(`${this.postsUrl}/addcomment/${postId}`, comment)
        .then(() => resolve())
        .catch(err => reject(err));
    })
  }
}
