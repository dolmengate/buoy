import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import axios from "axios";
import {Post} from "../model/Post";
import {PostService} from "../post.service";

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
    this.postService.getPost(Number(postId))
      .then((post) => this.post = post)
      .catch(err => console.log(err));
  }

  saveAndIncrement() {
    axios.post(`/api/posts/save/${this.post.postId}`, { headers: { "Content-Type": "application/json"} })
      .then(res => {
        this.post = this.formatPostDates(res.data);
      })
      .catch(err => console.log(err));
  }

  private formatPostDates(postData: any): Post {
    postData.created = new Date(postData.created.nano);
    postData.lastModified = new Date(postData.lastModified.nano);

    if (postData.comments !== undefined) {
      postData.comments.forEach(comment => {
        comment.created = new Date(comment.created.nano)
      });
    }
    return postData;
  }
}
