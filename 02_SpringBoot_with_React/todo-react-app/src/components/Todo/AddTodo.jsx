import React, { useState } from "react";
import { TextField, Paper, Button, Grid } from '@mui/material'

const AddTodo = (props) => {
  const [newTodoItem, setNewTodoItem] = useState("")
  
  const onInputChangeHandler = (event) => {
    const newTodo = { title: "" }
    newTodo.title = event.target.value
    setNewTodoItem(newTodo.title)
  }

  // 버튼 클릭시 add
  const onAddTodo = () => {
    props.addTodo(newTodoItem)
    setNewTodoItem("")
  }

  // input창에서 enter시 add
  const enterPressHandler = (event) => {
    if (event.key === 'Enter') {
      onAddTodo()
    }
  }

  return(
    <Paper style={{margin: 16, padding: 16}}>
      <Grid container>
        <Grid xd={11} md={11} item style={{paddingRight: 16}}>
          <TextField 
          placeholder="Add Todo Here" 
          fullWidth
          onChange={onInputChangeHandler}
          value={newTodoItem}
          onKeyUp={enterPressHandler}
          >
          </TextField>
        </Grid>
        <Grid xs={1} md={1} item>
          <Button 
          fullWidth color="secondary" 
          variant="outlined"
          onClick={onAddTodo}
          >
            +
          </Button>
        </Grid>
      </Grid>
    </Paper>
  )
}

export default AddTodo;