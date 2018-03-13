import {Comment} from "./Comment";

export class Post {

  constructor(postId?: number, author?: string, title?: string) {
    this.postId = postId;
    this.author = author;
    this.title = title;
    this.created = new Date();
    this.lastModified = new Date();
  }

  public postId: number;
  public author: string;
  public title: string;
  public description: string;
  public created: Date;
  public lastModified: Date;
  public type: string;
  public version: number;
  public comments: Comment[];
  public numComments: number;
  public editorId: number;

  // fixme: Attachment vars - temporary
  public contentText: string = '';

  /**
   * Builder function to unpack JSON object and return a Post instance.
   * @param source: {object}    JSON object to map to Post object. Maps to server-side PostDTO class.
   * @returns {Post}            Constructed Post object with comments constructed and mapped.
   */
  public static getInstance(source: any): Post {
    const p = Object.assign(new Post(), source);
    p.created = new Date(source.created);
    p.lastModified = new Date(source.lastModified);
    if (source.comments) {
      // map source data comments JSON into Comment objects
      p.comments = source.comments.map(c => {
        return Comment.getInstance(c, source.comments);
      });
    }
    return p;
  }
}
