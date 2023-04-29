import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SelectedPostList from './components/SelectedPostList';
import AddSelectedPost from './components/AddSelectedPost';
import EditSelectedPost from './components/EditSelectedPost';
import mockData from './mockData';

function App() {
  const [selectedPosts, setSelectedPosts] = useState(mockData.selectedPosts);
  const [posts, setPosts] = useState(mockData.posts);

  const addSelectedPost = (selectedPost) => {
    const newSelectedPosts = [...selectedPosts, selectedPost];
    setSelectedPosts(newSelectedPosts);
  };

  const updateSelectedPost = (id, updatedSelectedPost) => {
    const updatedSelectedPosts = selectedPosts.map((selectedPost) => {
      if (selectedPost.id === id) {
        return updatedSelectedPost;
      } else {
        return selectedPost;
      }
    });
    setSelectedPosts(updatedSelectedPosts);
  };

  const deleteSelectedPost = (id) => {
    const updatedSelectedPosts = selectedPosts.filter(
      (selectedPost) => selectedPost.id !== id
    );
    setSelectedPosts(updatedSelectedPosts);
  };

  const toggleSelectedPost = (id) => {
    const updatedSelectedPosts = selectedPosts.map((selectedPost) => {
      if (selectedPost.id === id) {
        return { ...selectedPost, active: !selectedPost.active };
      } else {
        return selectedPost;
      }
    });
    setSelectedPosts(updatedSelectedPosts);
  };

  return (
    <Router>
      <Routes>
        <Route path="/" element={<SelectedPostList selectedPosts={selectedPosts} onDeleteSelectedPost={deleteSelectedPost} onToggleSelectedPost={toggleSelectedPost}/>} />
        <Route path="/add" element={<AddSelectedPost posts={posts} onAddSelectedPost={addSelectedPost} />} />
        <Route path="/edit/:id" element={<EditSelectedPost posts={posts} selectedPosts={selectedPosts} onUpdateSelectedPost={updateSelectedPost} />} />
      </Routes>
    </Router>
  );
}

export default App;
