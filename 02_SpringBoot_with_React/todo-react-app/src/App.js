import './App.css';
import React, {useCallback, useEffect, useState} from 'react';
import Todo from './components/Todo/Todo'
import { Paper, List, Container } from '@mui/material'
import AddTodo from './components/Todo/AddTodo';
import { call } from './service/ApiService';

function App() {
  const [todoItems, setTodoItems] = useState([])
  const [error, setError] = useState(false)

  // const fetchTodoListHandler = useCallback( async () => {
  //   const requestOptions = {
  //     method: "GET",
  //     headers: {
  //       "Content-Type": "application/json"
  //     },
  //   }
  //   try {
  //     const res = await fetch("http://localhost:8080/todo", requestOptions)
  //     const data = await res.json()
  //     const fetchedTodoList = []
  //     for (const key in data) {
  //       fetchedTodoList.push(key.data)
  //     }
  //     setTodoItems(fetchedTodoList)
  //   } catch (error) {
  //     setError(error.message)
  //   }
  // }, [])

  // useEffect(() => {
  //   fetchTodoListHandler()
  // }, [fetchTodoListHandler])

  useEffect(() => {
    call("/todo", "GET", null).then(res => 
      setTodoItems(res.data)
    )
  }, [])

  const addTodo = (item) => {
    const newItem = { id: todoItems.length, title: item, done: false, readOnly: true }
    call("/todo", "POST", newItem).then(res =>
      setTodoItems(res.data)
    )
  }

  const deleteTodo = (item) => {
    call("/todo", "DELETE", item).then(res => 
      setTodoItems(res.data)
      )
  }

  const updateTodo = (item) => {
    call("/todo", "PUT", item).then(res => 
      setTodoItems(res.data)
      )
  }

  const checkTodo = (item) => {
    item.done = !item.done
    call("/todo", "PUT", item).then(res =>
      setTodoItems(res.data))
  }

  // const addTodo = (item) => {
  //   setTodoItems(prevTodoItems => {
  //     const updatedTodoItems = [...prevTodoItems]
  //     updatedTodoItems.push({id: "ID-" + prevTodoItems.length, title: item, done: false, readOnly: true})
  //     return updatedTodoItems
  //   })
  // }

  // const deleteTodo = (item) => {
  //   setTodoItems(prevTodoItems => {
  //     const updatedTodoItems = prevTodoItems.filter(todo => todo.id !== item.id)
  //     return updatedTodoItems
  //   })
  // }

  // const updateTodo = (item) => {
  //   setTodoItems(prevTodoItems => {
  //     const updatedTodoItems = [...prevTodoItems]
  //     for (const todo of updatedTodoItems) {
  //       if (todo.id === item.id) {
  //         todo.title = item.title
  //         break
  //       }
  //     }
  //     return updatedTodoItems
  //   })
  // }

  // const checkTodo = (item) => {
  //   setTodoItems(prevTodoItems => {
  //     const updatedTodoItems = [...prevTodoItems]
  //     for (const todo of updatedTodoItems) {
  //       if (todo.id === item.id) {
  //         todo.done = !todo.done
  //         break
  //       }
  //     }
  //     return updatedTodoItems
  //   })
  // }

  // 마우스 클릭 시 ReadOnly false, 엔터시 ReadOnly true
  const toggleReadOnly = (item, flag) => {
    setTodoItems(prevTodoItems => {
      const updatedTodoItems = [...prevTodoItems]
      for (const todo of updatedTodoItems) {
        if (todo.id === item.id) {
          todo.readOnly = flag
          break
        }
      }
      return updatedTodoItems
    })
  }

  const todoItemsList = todoItems.length > 0 && (
    <Paper style={{ margin: 16 }}>
      <List>
        {todoItems.map((i, idx) => (
          <Todo 
          item={i} key={i.id} 
          onDelete={deleteTodo} 
          onCheck={checkTodo} 
          toggleReadOnly={toggleReadOnly}
          onUpdate={updateTodo}
          ></Todo>
        ))}
      </List>

    </Paper>
  )

  return (
    <div className="App">
      <Container maxWidth="md">
        <AddTodo addTodo={addTodo}></AddTodo>
        {error && <div>{error}</div>}
        {!error && <div className='TodoList'>{todoItemsList}</div>}
      </Container>
    </div>
  );
}

export default App;
