import React from 'react';
import "./Landing.css"

const LandingPage = () => {
    return (
        <div className="landing-page">
            <video className="video-background" autoPlay muted>
                <source
                    src = "https://rr1---sn-g5qpw0n8x3uvg-cxbl.googlevideo.com/videoplayback?expire=1685126688&ei=wKlwZN-6CcbRvdIPuO69iAI&ip=156.255.25.182&id=o-ACPds5oz_YY0aHprWfdBCX9cANjou-scdsXUxGpWwHSZ&itag=248&aitags=133%2C134%2C135%2C136%2C137%2C160%2C242%2C243%2C244%2C247%2C248%2C278&source=youtube&requiressl=yes&spc=qEK7B4iitKCzNd5YhllpOcCiDmDm7fpt8tWdyWYnpg&vprv=1&svpuc=1&mime=video%2Fwebm&ns=I9E12sP7fkqZsQCY19CtlaYN&gir=yes&clen=9618166&dur=42.533&lmt=1669183271303379&keepalive=yes&fexp=24007246,24350017,24363392&beids=24350017&c=WEB&txp=5319224&n=SfZtJNWeHxSWkA&sparams=expire%2Cei%2Cip%2Cid%2Caitags%2Csource%2Crequiressl%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Cns%2Cgir%2Cclen%2Cdur%2Clmt&sig=AOq0QJ8wRAIgdja0k5V2pBeS2u97aglKqVZvVsa9Ea8lue9ikuylGVkCIC95vqTSHrFjRkVj_fclDBS6R7ABeDJSpcr3jkSc7jOc&redirect_counter=1&rm=sn-25gr67z&req_id=b13e22fb68f7a3ee&cms_redirect=yes&ipbypass=yes&mh=7A&mip=2a06:5b00:8d02:6700:f9b7:3d0b:e77e:61b8&mm=31&mn=sn-g5qpw0n8x3uvg-cxbl&ms=au&mt=1685104610&mv=m&mvi=1&pl=40&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRAIgLul3_MqmHYrY1q-_ceR92KvQFtyPEwYIdyUuvs8ownUCIEHLeVfXkOtXtEKAgOcUI8xPSg3oX4NJpJeYjKl_1kTp"         type="video/mp4"
                />
            </video>
        </div>
    );
};

export default LandingPage;