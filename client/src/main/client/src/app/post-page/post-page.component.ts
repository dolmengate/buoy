import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Post} from "../model/Post";
import {PostService} from "../services/post.service";

@Component({
  selector: 'app-post-page',
  templateUrl: './post-page.component.html',
  styleUrls: ['./post-page.component.css']
})
export class PostPageComponent implements OnInit {

  public post: Post;

  constructor(private route: ActivatedRoute, private postService: PostService) { }

  ngOnInit() {
    const postId = this.route.snapshot.paramMap.get("postId");
    this.postService.getPost(+postId)
      .then((post) => this.post = post)
      .catch(err => console.log(err));
  }

  saveAndIncrement() {
    this.postService.savePost(this.post);
  }
}
