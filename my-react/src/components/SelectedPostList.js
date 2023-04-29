import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

function SelectedPostList(props) {
  const { selectedPosts, onDeleteSelectedPost, onToggleSelectedPost } = props;

  return (
    <div>
      <h1>Selected Posts</h1>
      <table className="table">
        <thead>
          <tr>
            <th>#</th>
            <th>Package Name</th>
            <th>Position</th>
            <th>Post Title</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {selectedPosts.map((selectedPost, index) => (
            <tr key={index}>
              <td>{index + 1}</td>
              <td>{selectedPost.packageName}</td>
              <td>{selectedPost.position}</td>
              <td>{selectedPost.post ? selectedPost.post.title : ''}</td>
              <td>
                <Link to={`/edit/${selectedPost.id}`}>
                  <Button variant="warning" className="me-2">
                    Edit
                  </Button>
                </Link>
                <Button
                  variant={selectedPost.active ? 'success' : 'secondary'}
                  className="me-2"
                  onClick={() => onToggleSelectedPost(selectedPost.id)}
                >
                  {selectedPost.active ? 'Active' : 'Inactive'}
                </Button>
                <Button
                  variant="danger"
                  onClick={() => onDeleteSelectedPost(selectedPost.id)}
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/add">
        <Button variant="primary" className="mb-3">
          Add Post
        </Button>
      </Link>
    </div>
  );
}

export default SelectedPostList;
