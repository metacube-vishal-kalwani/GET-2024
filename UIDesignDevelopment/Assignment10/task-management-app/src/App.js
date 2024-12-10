import React, { useState } from 'react'
import './App.scss'
import { Container } from 'react-bootstrap'
import Header from './Components/Header/Header'
import Dashboard from './Components/Dashboard/Dashboard'
import NewTask from './Components/NewTask/NewTask'
const App = () => {



  const initTaskList = [
    {
      title: 'first',
      desc: 'Desc First',
      taskStatus: 'In Progress',
      creationDate: '',
      completionDate: '',
      priority: 0,
    },
    {
      title: 'first',
      desc: 'Desc First',
      taskStatus: 'Done',
      creationDate: '',
      completionDate: '',
      priority: 2,
    },
    {
      title: 'first',
      desc: 'Desc First',
      taskStatus: 'New',
      creationDate: '',
      completionDate: '',
      priority: 1,
    }
  ]

  const [taskList, setTaskList] = useState(initTaskList);
  const [modalShow, setModalShow] = useState(false);

  const createTask = (data) => {
    try {
      if (data.title == '' || data.desc == '') {
        throw new Error("title or description can't be empty");
      }
      let date = new Date();
      data.creationDate = ''
      data.taskStatus = 'New'
      setTaskList((prev) => ([...prev, data]))
    } catch (error) {
      console.log(error)
    }
  }
  console.log('Task created successfully', taskList)

  return (
    <Container >
      <NewTask
        show={modalShow}
        onHide={() => setModalShow(false)}
        createTask={createTask}
      />
      <Header setModalShow={setModalShow} />
      <Dashboard taskList={taskList} />
    </Container>
  )
}

export default App