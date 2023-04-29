const mockData = {
  posts: [
    {
      id: 1,
      title: "Post 1",
      content: "This is post 1",
      author: "Author 1",
      isActive: true,
      createdAt: "2022-01-01T00:00:00.000Z",
      updatedAt: "2022-01-01T00:00:00.000Z",
    },
    {
      id: 2,
      title: "Post 2",
      content: "This is post 2",
      author: "Author 2",
      isActive: false,
      createdAt: "2022-01-02T00:00:00.000Z",
      updatedAt: "2022-01-02T00:00:00.000Z",
    },
    // more posts...
  ],
  selectedPosts: [
    {
      id: 1,
      packageName: "Package 1",
      position: "Position 1",
      postId: 1,
      isActive: true,
      createdAt: "2022-01-01T00:00:00.000Z",
      updatedAt: "2022-01-01T00:00:00.000Z",
      post: {
        id: 1,
        title: "Post 1",
        content: "This is post 1",
        author: "Author 1",
        isActive: true,
        createdAt: "2022-01-01T00:00:00.000Z",
        updatedAt: "2022-01-01T00:00:00.000Z",
      },
    },
    {
      id: 2,
      packageName: "Package 2",
      position: "Position 2",
      postId: 2,
      isActive: false,
      createdAt: "2022-01-02T00:00:00.000Z",
      updatedAt: "2022-01-02T00:00:00.000Z",
      post: {
        id: 2,
        title: "Post 2",
        content: "This is post 2",
        author: "Author 2",
        isActive: false,
        createdAt: "2022-01-02T00:00:00.000Z",
        updatedAt: "2022-01-02T00:00:00.000Z",
      },
    },
    // more selected posts...
  ],
};

export default mockData;
