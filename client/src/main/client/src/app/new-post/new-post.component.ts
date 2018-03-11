import { Component, OnInit } from '@angular/core';
import {Post} from "../model/Post";
import {PostService} from "../post.service";

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  TYPES: string[] = ['EDITOR', 'TEXT'];
  post: Post;

  constructor(private postService: PostService) { }

  ngOnInit() {
    this.post = new Post();
  }

  onSubmit() {
    this.postService.newPost(this.post)
      .catch(err => console.log(err));
  }
}
