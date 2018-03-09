export class Comment {

  constructor(id?: number, author?: string, text?: string) {
    this.commentId = id;
    this.author = author;
    this.text = text;
    this.replies = [];
  }

  public commentId: number;
  public author: string;
  public text: string;
  public created: Date;
  public replyTo: Comment;
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

    // find replies for Comment out of all post comments
    if (allComments) {
      allComments.forEach((comment, i) => {
        if (comment.replyTo !== null)
          if (comment.replyTo.commentId === c.commentId) // allComments passed by value: original is not changed
            c.replies.push(allComments.splice(i, 1)[0]);
      });
    }
    return c;
  }
}
