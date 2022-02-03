import { Avatar, Button, CardActions, CardContent, CardHeader, Divider } from "@mui/material"
import { Box } from "@mui/system"
import { makeStyles } from '@mui/styles';
import { grey } from "@mui/material/colors";
import { useState } from "react";
import { CommentForm } from "./CommentForm";
import { boardStyles } from "../../Board.styles";
import MoreBtn from "../../../../Components/MoreBtn";
import getTime from "../../helper";

const useStyles = makeStyles(boardStyles)

export const SingleComment = ({
  comments, 
  setComments, 
  comment,
  addComment, 
  addCommentLike, 
  updateComment}) => {
  const classes = useStyles();

  const [openForm, setOpenForm] = useState(false);
  const [content, setContent] = useState("");
  const [updateFlag, setUpdateFlag] = useState(false);
  const openCommentForm = () => {
    if (openForm) {
      setOpenForm(false)
    } else {
      setOpenForm(true)
    }
    setContent("")
    setUpdateFlag(false)
  };
  
  return (
    <Box className={comment.responseTo === -1 ? classes.comments : classes.nestedComment} key={comment.id}>
      <CardHeader
        avatar={
          comment.responseTo === -1 ? 
          <Avatar sx={{ bgcolor: grey[500] }} aria-label="recipe">              
          </Avatar>
          :
          <>
            <span 
              className={classes.replyIcon}
              style={{ position: 'relative', left: '-30px'}}
            >ㄴ</span>
            <Avatar sx={{ bgcolor: grey[500], right: '25.2px' }} aria-label="recipe">              
            </Avatar>
          </>
        }
        action={
          <MoreBtn
            editData={comment} 
            type="comment" 
            comments={comments} 
            setComments={setComments} 
            setOpenForm={setOpenForm} 
            setContent={setContent} 
            setUpdateFlag={setUpdateFlag} 
          />
        }
        title={comment.writer}
        subheader={getTime(comment.date)}
        className={classes.commentDateText}
        sx={ comment.responseTo !== -1 ? {'& .MuiCardHeader-content': {position: 'relative', right: '25px'}} : null }
      />
      <CardContent>
        <p className={classes.commentContent}>
          {comment.content}
        </p>
      </CardContent>
      <CardActions disableSpacing sx={{display: 'flex', justifyContent: 'space-between'}}>
        <span>
          {comment.responseTo === -1 && <Button className={classes.botText} onClick={openCommentForm} component="span">답글쓰기</Button>}
          <Button className={classes.botText} onClick={() => addCommentLike(comment)} component="span"> 좋아요 {comment.like}</Button>
        </span>            
      </CardActions>      
      <Divider />
      
      {/* 댓글 or 대댓글 작성창 */}
      {openForm &&
        <CommentForm 
          comment={comment} 
          addComment={addComment} 
          setOpenForm={setOpenForm} 
          content={content} 
          setContent={setContent} 
          updateComment={updateComment}
          updateFlag={updateFlag}
          setUpdateFlag={setUpdateFlag}
        />
      }   
    </Box>    
  )
}