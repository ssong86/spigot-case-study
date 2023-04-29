import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';

function EditSelectedPost(props) {
  const { posts, selectedPosts, onUpdateSelectedPost } = props;
  const { id } = useParams();
  const navigate = useNavigate();

  const selectedPost = selectedPosts.find((selectedPost) => selectedPost.id === parseInt(id));

  const [packageName, setPackageName] = useState(selectedPost.packageName);
  const [position, setPosition] = useState(selectedPost.position);

  const handleSubmit = (event) => {
    event.preventDefault();
    const updatedSelectedPost = {
      id: selectedPost.id,
      packageName: packageName,
      position: position,
      postId: selectedPost.postId,
      active: selectedPost.active,
    };
    onUpdateSelectedPost(selectedPost.id, updatedSelectedPost);
    navigate('/');
  };

  return (
    <>
      <h2>Edit Selected Post</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formPackageName">
          <Form.Label>Package Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter package name"
            value={packageName}
            onChange={(event) => setPackageName(event.target.value)}
          />
        </Form.Group>
        <Form.Group controlId="formPosition">
          <Form.Label>Position</Form.Label>
          <Form.Control
            type="number"
            placeholder="Enter position"
            value={position}
            onChange={(event) => setPosition(event.target.value)}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          Save Changes
        </Button>
      </Form>
    </>
  );
}

export default EditSelectedPost;
