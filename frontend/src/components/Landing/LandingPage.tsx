import React from 'react';
import "./Landing.css"

const LandingPage = () => {
    return (
        <div className="landing-page">
            <video className="video-background" autoPlay muted>
                <source
                    src = "https://rr1---sn-g5qpw0n8x3uvg-cxbl.googlevideo.com/videoplayback?expire=1685046182&ei=Rm9vZL7zFJG99QPn_paoDQ&ip=103.165.169.134&id=o-AL1sVPzsPbgVNrvA-bwFgylVSMPtZrBkt3sqVcxmeoK7&itag=248&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C278&source=youtube&requiressl=yes&spc=qEK7ByPa7_eGu2wQkTIgKCx8w4WxPlnldWYzcC6zXA&vprv=1&svpuc=1&mime=video%2Fwebm&ns=552B3FDBgd1wkBql-_1rx4IN&gir=yes&clen=9618166&dur=42.533&lmt=1669183271303379&keepalive=yes&fexp=24007246,51000024&c=WEB&txp=5319224&n=t1AAFEktYjLTaA&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRgIhAMr0MURn_TdsoqPHr9Fwr43RdGiPYSFMzM_EiwnlzRtcAiEA6CFk2zfWShJ5-HrR-NzE-1fj1ULrzlEaTVcKejYDIlE%3D&redirect_counter=1&rm=sn-cvhz676&req_id=8a7aab83a1c5a3ee&cms_redirect=yes&ipbypass=yes&mh=7A&mip=2a06:5b00:8d02:6700:5570:cacb:7663:a118&mm=31&mn=sn-g5qpw0n8x3uvg-cxbl&ms=au&mt=1685038367&mv=m&mvi=1&pcm2cms=yes&pl=40&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pcm2cms,pl&lsig=AG3C_xAwRQIgbaQUdC27McPp2kV9K2Ye5BU3I0sh0yTergPclvmpzEsCIQDqjXH_xfllr-Cx7o3zrqfzxjjcXJ1yLEebXI7OESyxjQ%3D%3D"
                    type="video/mp4"
                    />
            </video>
        </div>
    );
};

export default LandingPage;
