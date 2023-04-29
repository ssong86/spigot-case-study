import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import mockData from '../mockData';

function AddSelectedPost(props) {
  const { onAddSelectedPost } = props;
  const [packageName, setPackageName] = useState('');
  const [position, setPosition] = useState('');
  const [postId, setPostId] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    const newSelectedPost = {
      id: Math.max(...mockData.selectedPosts.map((p) => p.id)) + 1,
      packageName,
      position,
      postId: Number(postId),
      isActive: true,
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString(),
      post: mockData.posts.find((p) => p.id === Number(postId)),
    };
    onAddSelectedPost(newSelectedPost);
    navigate('/');
  };

  return (
    <>
      <h2>Add Selected Post</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3" controlId="packageName">
          <Form.Label>Package Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter package name"
            value={packageName}
            onChange={(event) => setPackageName(event.target.value)}
            required
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="position">
          <Form.Label>Position</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter position"
            value={position}
            onChange={(event) => setPosition(event.target.value)}
            required
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="postId">
          <Form.Label>Post</Form.Label>
          <Form.Control
            as="select"
            value={postId}
            onChange={(event) => setPostId(event.target.value)}
            required
          >
            <option value="">Select a post</option>
            {mockData.posts.map((post) => (
              <option key={post.id} value={post.id}>
                {post.title}
              </option>
            ))}
          </Form.Control>
        </Form.Group>

        <Button variant="primary" type="submit">
          Add
        </Button>
      </Form>
    </>
  );
}

export default AddSelectedPost;
