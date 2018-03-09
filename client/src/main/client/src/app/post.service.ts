import { Injectable } from '@angular/core';
import {Post} from "./model/Post";
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

  savePost(postId: number, post: Post): void {
    // todo: implement function
  }
}
