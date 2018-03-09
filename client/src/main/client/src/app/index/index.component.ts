import { Component, OnInit } from '@angular/core';
import { Post } from "../model/Post";
import {PostService} from "../post.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  public posts: Post[];

  constructor(private postService: PostService) { }

  ngOnInit() {
    this.postService.getPosts()
      .then((posts) => { this.posts = posts; })
      .catch((err) => console.log(err));
  }
}
