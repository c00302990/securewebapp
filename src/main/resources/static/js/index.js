window.addEventListener("DOMContentLoaded", () => {
    const token = localStorage.getItem("accessToken");

    const loginBtn = document.getElementById("login-btn");
    const signupBtn = document.getElementById("signup-btn");
    const logoutBtn = document.getElementById("logout-btn");

    if(token){
        loginBtn.style.display = "none";
        signupBtn.style.display = "none";
        logoutBtn.style.display = "block";
    } else{
            loginBtn.style.display = "block";
            signupBtn.style.display = "block";
            logoutBtn.style.display = "none";
    }

    logoutBtn.addEventListener("click", async (e) => {
        e.preventDefault();

        try {
                const response = await fetch("/api/users/logout", {
                    method: "POST",
                    headers: {
                        "Authorization": `Bearer ${token}`
                    }
                });

                if(response.ok){
                    localStorage.removeItem("accessToken");
                    alert("로그아웃 되었습니다.");
                    window.location.href = "/index.html";
                } else{
                    alert("로그아웃 실패");
                }
        } catch(error){
            console.error("로그아웃 오류: ", error);
            alert("에러가 발생했습니다.");
        }
    });
});