import React, { useState, useEffect } from "react";
import axios from "axios";

interface User {
  id: number;
  firstName: string;
  lastName: string;
  phoneNumber: number;
  status: string;
  email: string;
  address: string;
  dob: string;
}

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

const maskString = (param: string) => { const maskParam = param.substring(0, 2) + '*'.repeat(param.length - 2);
      return maskParam};



const UsersList: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]); // Specify the User[] type
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    api.get<User[]>("/users").then((response) => { // Specify the User[] type
      setUsers(response.data);
    });
  }, []);

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  const filteredUsers = users.filter((user) => {
    return user.firstName.toLowerCase().includes(searchTerm.toLowerCase()) ||
      user.email.toLowerCase().includes(searchTerm.toLowerCase());
  });

  const handleDeleteUser = (userId: number) => { // Specify the number type for userId
    api.delete(`/users/${userId}`).then(() => {
      setUsers(users.filter((user) => user.id !== userId));
    });
  };

  const handleCreateUser = () => {
    const newUser: User = { // Specify the User type
      id: users.length + 1, // Generate a unique ID (you might want to use a better approach in a real backend)
      firstName: "firstname",
      lastName: "lastname4",
      phoneNumber:123456789,
      status: "active",
      email: "newuser@example.com",
      dob: "10-01-2023",
      address: "Test"
    };

    api.post("/users", newUser).then(() => {
      setUsers([...users, newUser]);
    });
  };

  const handleUpdateUser = (userId: number, updatedUser: User) => { // Specify types for userId and updatedUser
    api.put(`/users/${userId}`, updatedUser).then(() => {
      const updatedUsers = users.map((user) => {
        if (user.id === userId) {
          return updatedUser;
        }
        return user;
      });
      setUsers(updatedUsers);
    });
  };

  return (
    <div>
      <h1>User Management</h1>
      <input
        type="text"
        placeholder="Search users..."
        value={searchTerm}
        onChange={handleSearch}
      />
      <br/><br/>
      <table style={{ border: '2px solid black' }}>
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone Number</th>
            <th>Status</th>
            <th>Email</th>
            <th>dob</th>
            <th>address</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody style={{ border: '2px solid black' }}>
          {filteredUsers.map((user) => (
            <tr key={user.id} style={{ border: '2px solid black' }}>
              <td  style={{ border: '2px solid black' }}>{user.firstName}</td>
              <td  style={{ border: '2px solid black' }}>{user.lastName}</td>
              <td  style={{ border: '2px solid black' }}>{user.phoneNumber}</td>
              <td  style={{ border: '2px solid black' }}>{user.status}</td>
              <td  style={{ border: '2px solid black' }}>{user.email}</td>
              <td  style={{ border: '2px solid black' }}>{maskString(user.address)}</td>
              <td  style={{ border: '2px solid black' }}>{maskString(user.dob)}</td>
              <td  style={{ border: '2px solid black' }}>
                <button onClick={() => handleDeleteUser(user.id)}>Delete</button>
                <button onClick={() => handleUpdateUser(user.id, { ...user, firstName: "Updated Name" })}>
                  Update
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <br/><br/>
      <button onClick={handleCreateUser}>Create User</button>
    </div>
  );
};

export default UsersList;
