import { Injectable } from '@angular/core';
import {Post} from "../model/Post";
import axios from "axios";

@Injectable()
export class PostService {

  private postsUrl: string = '/api/posts';

  constructor() { }

  /**
   * Get all Posts.
   * @returns {Promise<Post[]>}
   */
  getPosts(): Promise<Post[]> {
    return new Promise((resolve, reject) => {
      axios.get(this.postsUrl)
        .then(res => resolve(res.data.map(p => {return Post.getInstance(p);})))
        .catch(err => { reject(err);});
    });
  }

  /**
   * Get a single Post by id.
   * @param {number} postId
   * @returns {Promise<Post>}
   */
  getPost(postId: number): Promise<Post> {
    return new Promise((resolve, reject) => {
      const singlePostUrl = `${this.postsUrl}/${postId}`;
      axios.get(singlePostUrl)
        .then(res => { resolve(Post.getInstance(res.data)); })
        .catch(err => reject(err));
    });
  }

  /**
   * Save a single Post.
   * @param {Post} post
   * @returns {Promise<void>}
   */
  savePost(post: Post): Promise<void> {
    return new Promise((resolve, reject) => {
      const savePostUrl = `${this.postsUrl}/save/${post.postId}`;
      axios.post(savePostUrl, post)
        .then(() => resolve())
        .catch((err) => reject(err));
    })
  }

  /**
   * Save a new Post.
   * @param {Post} post
   * @returns {Promise<Post>}
   */
  newPost(post: Post): Promise<Post> {
    return new Promise((resolve, reject) => {
      axios.post(`${this.postsUrl}/new`, post)
        .then((res) => resolve(Post.getInstance(res.data)))
        .catch((err) => reject(err));
    })
  }
}
