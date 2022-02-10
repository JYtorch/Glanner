import { Avatar, CardActions, CardContent, CardHeader, Chip, Divider, IconButton, Stack } from "@mui/material"
import { grey } from "@mui/material/colors"
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';
import { makeStyles } from "@mui/styles";
import { boardStyles } from "../../Board.styles";
import MoreBtn from "../../../../Components/MoreBtn";
import moment from "moment";
import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import { GroupMember } from "./GroupButton";

const useStyles = makeStyles(boardStyles);

export const DetailBody = ({ post, addLike }) => {
  const classes = useStyles();
  const { pathname } = useLocation();
  const [groupPage, setGroupPage] = useState(false);

  useEffect(() => {
    if (pathname.includes('/group/')) {
      setGroupPage(true)      
    }
  }, [pathname])

  return (
    <>    
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: grey[500] }} aria-label="recipe">              
          </Avatar>
        }
        action={
          groupPage ?
          <div style={{display: 'flex', justifyContent: 'space-between', width: '170px'}}>
              <GroupMember post={post} />
              <MoreBtn editData={post} type={`/group/body`} />
          </div>
          :
          <MoreBtn editData={post} type={`/free/body`} />
        }
        
        title={post.userEmail}
        subheader={moment(post.createdDate).format('YYYY.MM.DD HH:mm:ss')}
        className={classes.dateText}
      />
      <Divider />
      {/* {post.attachment && <CardMedia
        component="img"
        height="194"
        image={post.attachment}
        alt="image"
      />} */}
      <CardContent>
        <h1 className={classes.title}>
          {post.title}
        </h1>
        <p className={classes.content}>
          {post.content}
        </p>
      </CardContent>
      {groupPage &&
      <Stack spacing={1} sx={{mt: 5}}>        
        <Stack direction="row" spacing={1}>          
          {/* {post.tags && post.tags.map((title, idx) => {
            return <Chip label={title} size="small" sx={{borderRadius: '5px', color: 'white', backgroundColor: "#8C7B80"}} key={idx} />
          })} */}
          <Chip label={post.interests} size="small" sx={{borderRadius: '5px', color: 'white', backgroundColor: "#8C7B80"}} />
        </Stack>
      </Stack>}
      <CardActions disableSpacing sx={{display: 'flex', justifyContent: 'space-between'}}>
        <span className={classes.botText}>조회수 {post.count}{post.likeCount ? <>{`, 좋아요 ${post.likeCount}`}</>: null } </span>
        <div>
          <IconButton onClick={addLike}>
            <FavoriteIcon />
          </IconButton>
          <IconButton aria-label="share">
            <ShareIcon />
          </IconButton>
        </div>          
      </CardActions>
    </>
  )
}