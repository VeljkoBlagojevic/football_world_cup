import "./Landing.css"

const LandingPage = () => {
    const videoLink = "https://rr1---sn-g5qpw0n8x3uvg-cxbl.googlevideo.com/videoplayback?expire=1687631249&ei=MeGWZMLkDMrJgwOTjKaABA&ip=66.180.147.164&id=o-APQjXNNy-TV1q_EgP6TqOM6nVs5A6_9o3PgXCWtio-DK&itag=247&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C278&source=youtube&requiressl=yes&spc=qEK7B9Ke3J3JIurGKIqs61ZYQOGB8tEi4RoPWgdoGg&vprv=1&svpuc=1&mime=video%2Fwebm&ns=i669F0kPD0Caf6JJbkW8jC0N&gir=yes&clen=6460776&dur=42.533&lmt=1669183270306694&keepalive=yes&fexp=24007246,24362688,24363391&c=WEB&txp=5319224&n=XNvTi-lXuZNjwg&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRQIhAJxTF7qqoe_DWD8KlUYoZBtvCr4ZpRZsoXtKrrakyi8pAiBubtWgiF7o1aVEwP8wjGWWtnR0V6e3qdBhQ_DJzTgoEw%3D%3D&redirect_counter=1&rm=sn-ab5eey7s&req_id=89658ad7517ea3ee&cms_redirect=yes&cmsv=e&ipbypass=yes&mh=7A&mip=2a06:5b00:8d02:6700:cce5:fb5b:4572:5690&mm=31&mn=sn-g5qpw0n8x3uvg-cxbl&ms=au&mt=1687609532&mv=m&mvi=1&pcm2cms=yes&pl=40&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pcm2cms,pl&lsig=AG3C_xAwRQIhAOhqza8quAYyIyo-v6gD5m2vysmG2o_Pj5cQZZhOmD9OAiA9IEarMyKtnLszuHfkZAHJroxRAGFw-zKWokTz2ssTxw%3D%3D";
    return (
        <div className="landing-page">
            <video className="video-background" autoPlay muted>
                <source
                    src={videoLink}
                    type="video/mp4"
                />
            </video>
        </div>
    );
};

export default LandingPage;