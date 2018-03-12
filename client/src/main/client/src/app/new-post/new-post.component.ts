import { Component, OnInit } from '@angular/core';
import {Post} from "../model/Post";
import {PostService} from "../post.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  TYPES: string[] = ['EDITOR', 'TEXT'];
  post: Post = new Post();

  constructor(private router: Router, private postService: PostService) { }

  ngOnInit() { }

  onSubmit() {
    this.postService.newPost(this.post)
      .then((post) => {
        console.log('new post component post', post);
        this.router.navigateByUrl(`/posts/${post.postId}`);
      })
      .catch(err => console.log(err));
  }
}
