export class Comment {

  constructor(author?: string, text?: string) {
    this.commentId = 0;
    this.author = author;
    this.text = text;
    this.created = new Date();
    this.replies = [];
    this.replyTo = null;
    this.replyToId = null;
  }

  public commentId: number;
  public author: string;
  public text: string;
  public created: Date;
  public replyTo: Comment;

  // for the purposes of sending comment replies to server as JSON
  // leave replyTo null and use replyToId with parent Comment's id
  // circular JSON objects cannot be serialized
  public replyToId: number;
  public replies: Comment[];

  /**
   * Builder function to unpack JSON object and return Comment instance.
   * @param {object} source           JSON object to map to Comment object. Maps to server-side Comment object with the
   *                                  exception of "replies" property.
   * @param {Comment[]} allComments   Array of Comments that the "source" object is a value of. Used to find a comment's
   *                                  replies.
   * @returns {Comment}               Constructed Comment object with its replies mapped.
   */
  public static getInstance(source: any, allComments?: Comment[]): Comment {
    const c = Object.assign(new Comment(), source);
    c.created = new Date(source.created);

    // find replies for Comment out of all post comments
    if (allComments) {
      allComments.forEach((comment, i) => {
        if (comment.replyTo !== null)
          if (comment.replyTo.commentId === c.commentId)
            c.replies.push(Comment.getInstance(allComments[i]));
      });
    }
    return c;
  }
}
